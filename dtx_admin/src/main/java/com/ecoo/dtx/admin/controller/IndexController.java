package com.ecoo.dtx.admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.pojos.ServiceResponse;

import com.ecoo.dtx.admin.DtxAdminApplication;
import com.ecoo.dtx.admin.dto.DtxActorDto;
import com.ecoo.dtx.admin.dto.DtxDto;
import com.ecoo.dtx.admin.dto.PageDto;
import com.ecoo.dtx.admin.pageable.Page;
import com.ecoo.dtx.admin.rpc.AppModel;
import com.ecoo.dtx.admin.rpc.HessianClient;
import com.ecoo.dtx.admin.service.DtxTransactionService;
import com.ecoo.dtx.model.DtxTransaction;
import com.ecoo.dtx.model.DtxTransactionActor;
import com.ecoo.dtx.model.query.TranActorCondition;
import com.ecoo.dtx.model.query.TranCondition;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mysql.jdbc.StringUtils;

@Controller
public class IndexController {

	@Autowired
	HessianClient hessianClient;

	@Autowired
	DtxTransactionService dtxTransactionService;

	@RequestMapping("/index")
	String index(HttpServletRequest request ) {
		request.getSession(true);
		return "index";
	}

	@RequestMapping("/dtx/list")
	String list() {

		return "dtx/list";
	}

	@RequestMapping("/getDtxList")
	@ResponseBody
	PageDto<DtxTransaction> getlist(TranCondition tranCondition) {

		Page<DtxTransaction> result = dtxTransactionService.getPageList(tranCondition);

		PageDto<DtxTransaction> pageDto = new PageDto<>();
		pageDto.setRows(result.getContent());
		pageDto.setTotal(result.getTotalElements());

		return pageDto;
	}

	@RequestMapping("/getDtxActors")
	@ResponseBody
	List<DtxTransactionActor> getDtxActors(@RequestParam String tranId) {

		TranActorCondition condition = new TranActorCondition();
		condition.setTranId(tranId);
		List<DtxTransactionActor> dtxList = dtxTransactionService.queryActors(condition);

		return dtxList;
	}

	@RequestMapping("/retry")
	@ResponseBody
	ServiceResponse retry(@RequestBody DtxActorDto dtxActorDto) throws Exception {

		DtxTransactionActor tranActor = dtxTransactionService.getById(dtxActorDto.getId());
		ServiceResponse serviceResponse = hessianClient.invoke(tranActor.getClassName(), tranActor.getParam(),
				AppModel.valueOf(tranActor.getModel()));
		Map<Object, Object> model = serviceResponse.getModel();
		if ("0".equals(model.get("state").toString())) {

		}

		return serviceResponse;
	}

}