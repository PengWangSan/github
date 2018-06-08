package com.ecoo.dtx.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ecoo.dtx.model.DtxTransactionActor;
import com.ecoo.dtx.model.query.TranActorCondition;
@Mapper
public interface DtxTransactionActorMapper {
    int insert(DtxTransactionActor record);
    
    int insertBatch(List<DtxTransactionActor> record);

    List<DtxTransactionActor> selectAll();

	List<DtxTransactionActor> queryActors(TranActorCondition condition);
}