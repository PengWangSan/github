package com.ecoo.dtx.admin.rpc;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.action.Action;
import org.springframework.web.servlet.pojos.ServiceRequest;
import org.springframework.web.servlet.pojos.ServiceResponse;

import com.ecoo.dtx.admin.context.ApplicationContextHolder;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class HessianClient {

	@Autowired
	ApplicationContextHolder applicationContextHolder;

	public ServiceResponse invoke(String className, String param, AppModel model)
			throws Exception {

		ServiceRequest serviceRequst = new ServiceRequest();
		serviceRequst.setRequestServiceId(className);

		ObjectMapper objectMapper = new ObjectMapper();
		Map conditon = objectMapper.readValue(param, Map.class);

		serviceRequst.setRequestObject(conditon);
		Action app = (Action) applicationContextHolder.getBean(model + "App");

		return app.perform(serviceRequst);

	}

	public ServiceResponse invoke(ServiceRequest serviceRequst, AppModel model) {

		Action app = (Action) applicationContextHolder.getBean(model + "App");

		return app.perform(serviceRequst);

	}

}
