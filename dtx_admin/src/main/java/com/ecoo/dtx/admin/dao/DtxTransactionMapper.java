package com.ecoo.dtx.admin.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ecoo.dtx.admin.pageable.Page;
import com.ecoo.dtx.admin.pageable.Pageable;
import com.ecoo.dtx.model.DtxTransaction;
import com.ecoo.dtx.model.query.TranCondition;
@Mapper
public interface DtxTransactionMapper {
    int insert(DtxTransaction record);
    
    
    int update(DtxTransaction record);

    List<DtxTransaction> selectAll();
    
    
    Page<DtxTransaction> getPageList(@Param("queryParam") TranCondition tranCondition, Pageable pageRequest);
}