package com.ecoo.dtx.admin.ws;

import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Component;

import com.ecoo.dtx.admin.config.GetHttpSessionConfigurator;
import com.ecoo.dtx.admin.ws.dto.MsgTypeEnum;

@ServerEndpoint(value = "/websocket", configurator = GetHttpSessionConfigurator.class)
@Component
public class ClientWebSocket extends WebSocket{

	
	@Override
	public MsgTypeEnum getMsgType() {
		return MsgTypeEnum.CLIENT;
	}
}
