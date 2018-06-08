package com.ecoo.dtx.admin.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.pojos.ServiceResponse;

import com.ecoo.dtx.admin.DtxAdminApplication;
import com.ecoo.dtx.admin.dto.DtxActorDto;
import com.ecoo.dtx.admin.dto.DtxDto;
import com.ecoo.dtx.admin.dto.PageDto;
import com.ecoo.dtx.admin.pageable.Page;
import com.ecoo.dtx.admin.rpc.AppModel;
import com.ecoo.dtx.admin.rpc.HessianClient;
import com.ecoo.dtx.admin.service.DtxTransactionService;
import com.ecoo.dtx.admin.ws.WebSocket;
import com.ecoo.dtx.model.DtxTransaction;
import com.ecoo.dtx.model.DtxTransactionActor;
import com.ecoo.dtx.model.query.TranActorCondition;
import com.ecoo.dtx.model.query.TranCondition;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.mysql.jdbc.StringUtils;

@Controller
public class ChatController {

	@Autowired
	HessianClient hessianClient;

	@Autowired
	DtxTransactionService dtxTransactionService;

	@RequestMapping("/chat/index")
	ModelAndView index() {
		
		
		
		
		return new ModelAndView("chat/index", "sockets", WebSocket.sockets);
	}


}