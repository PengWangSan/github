package com.ecoo.dtx.admin.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.ecoo.dtx.admin.pageable.Page;
import com.ecoo.dtx.admin.pageable.Pageable;
import com.ecoo.dtx.model.DtxTransaction;
import com.ecoo.dtx.model.DtxTransactionActor;
import com.ecoo.dtx.model.query.TranActorCondition;
import com.ecoo.dtx.model.query.TranCondition;


public interface DtxTransactionService {
	
	
	
	   Page<DtxTransaction> getPageList(TranCondition tranCondition);
	
	
	  int begin(DtxTransaction record);
	  
	  
	  int finish(DtxTransaction record);
	
	  List<DtxTransaction> query();
	  
	  
	  List<DtxTransactionActor> queryActors(TranActorCondition condition);
	  
	  DtxTransactionActor getById(String id);
	  
	  
	  void updateTranActorStatus(String id,Integer status);

}
