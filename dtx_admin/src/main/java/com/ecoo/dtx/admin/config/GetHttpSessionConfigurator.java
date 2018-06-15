package com.ecoo.dtx.admin.config;

import javax.servlet.http.HttpSession;
import javax.websocket.HandshakeResponse;
import javax.websocket.server.HandshakeRequest;
import javax.websocket.server.ServerEndpointConfig;
import javax.websocket.server.ServerEndpointConfig.Configurator;

import org.apache.catalina.session.StandardSessionFacade;

public class GetHttpSessionConfigurator extends Configurator {
	@Override
    public void modifyHandshake(ServerEndpointConfig sec,HandshakeRequest request, HandshakeResponse response) {
	      /*如果没有监听器,那么这里获取到的HttpSession是null*/
        StandardSessionFacade ssf = (StandardSessionFacade) request.getHttpSession();
        if (ssf != null) {
            HttpSession session = (HttpSession) request.getHttpSession();
            sec.getUserProperties().put(HttpSession.class.getName(), session);
        }
        sec.getUserProperties().put("name", "小强");
        super.modifyHandshake(sec, request, response);
    }
}
