package com.ecoo.dtx.admin.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecoo.dtx.admin.dao.DtxTransactionActorMapper;
import com.ecoo.dtx.admin.dao.DtxTransactionMapper;
import com.ecoo.dtx.admin.pageable.Page;
import com.ecoo.dtx.admin.pageable.Pageable;
import com.ecoo.dtx.admin.service.DtxTransactionService;
import com.ecoo.dtx.model.DtxTransaction;
import com.ecoo.dtx.model.DtxTransactionActor;
import com.ecoo.dtx.model.DtxTransactionStatusEnum;
import com.ecoo.dtx.model.query.TranActorCondition;
import com.ecoo.dtx.model.query.TranCondition;

@Service
public class DtxTransactionServiceImpl implements DtxTransactionService {

	@Autowired
	DtxTransactionMapper dtxTransactionMapper;

	@Autowired
	DtxTransactionActorMapper dtxTransactionActorMapper;

	@Override
	public Page<DtxTransaction> getPageList(TranCondition tranCondition) {
		
		
		
		Page<DtxTransaction> result=dtxTransactionMapper.getPageList(tranCondition, new Pageable(tranCondition.getPage()-1, tranCondition.getRows()));
		
		return result;
	}
	
	
	@Override
	public int begin(DtxTransaction record) {
		return dtxTransactionMapper.insert(record);
	}

	@Override
	public int finish(DtxTransaction dtxTransaction) {
		
		if (dtxTransaction.getTransactionActors() != null) {
			dtxTransactionActorMapper.insertBatch(dtxTransaction.getTransactionActors());
		}
		boolean succes=false;
		if (dtxTransaction.getTransactionActors() != null) {
			succes=dtxTransaction.getTransactionActors().stream().allMatch(a -> DtxTransactionStatusEnum.Succes.getCode()==a.getStatus().intValue());
		}
		dtxTransaction.setStatus(succes?DtxTransactionStatusEnum.Succes.getCode():DtxTransactionStatusEnum.FAILED.getCode());
		
		updateStatus(dtxTransaction.getTranId(), dtxTransaction.getStatus(),dtxTransaction.getUpdateTime());
		return 0;
	}

	@Override
	public List<DtxTransaction> query() {
		return dtxTransactionMapper.selectAll();
	}

	@Override
	public List<DtxTransactionActor> queryActors(TranActorCondition condition) {
		return dtxTransactionActorMapper.queryActors(condition);
	}

	private void updateStatus(String tranId, Integer status,Date updateDate) {
		DtxTransaction dtxTransaction = new DtxTransaction();
		dtxTransaction.setTranId(tranId);
		dtxTransaction.setStatus(status);
		
		if(updateDate!=null) {
			dtxTransaction.setUpdateTime(new Date());
		}
		
		dtxTransactionMapper.update(dtxTransaction);

	}

	@Override
	public DtxTransactionActor getById(String id) {

		TranActorCondition condition = new TranActorCondition();
		condition.setId(id);

		List<DtxTransactionActor> result = dtxTransactionActorMapper.queryActors(condition);

		if (result != null && !result.isEmpty()) {
			return result.get(0);
		} else {
			return null;
		}

	}

	@Override
	public void updateTranActorStatus(String id, Integer status) {
	
	  //TODO
	}

	

}
