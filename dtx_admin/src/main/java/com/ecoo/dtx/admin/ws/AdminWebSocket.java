package com.ecoo.dtx.admin.ws;

import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

import com.ecoo.dtx.admin.config.GetHttpSessionConfigurator;
import com.ecoo.dtx.admin.ws.dto.MsgTypeEnum;

@ServerEndpoint(value="/admin/websocket",configurator=GetHttpSessionConfigurator.class)
@Component
public class AdminWebSocket extends WebSocket{

	@Override
	public MsgTypeEnum getMsgType() {
		return MsgTypeEnum.AMDIN;
	}


	
	
	
}
