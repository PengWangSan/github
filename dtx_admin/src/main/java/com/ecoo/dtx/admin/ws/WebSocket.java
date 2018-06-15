package com.ecoo.dtx.admin.ws;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ecoo.dtx.admin.ws.cmd.MsgProcessor;
import com.ecoo.dtx.admin.ws.dto.MsgEntity;
import com.ecoo.dtx.admin.ws.dto.MsgTypeEnum;
import com.fasterxml.jackson.databind.ObjectMapper;


public abstract class WebSocket {

	public Logger logger = LoggerFactory.getLogger(WebSocket.class);

	private Session session;
	
	private HttpSession httpSession;

	private ObjectMapper objectMapper=new ObjectMapper();


	@OnOpen
	public void onOpen(Session session, EndpointConfig config) {

		logger.info("————————————连接开始");

		
		HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
		this.session = session;
		this.httpSession=httpSession;
		SocketHolder.getSockets().put(httpSession.getId(), this);
	}

	/**
	 * 连接关闭调用的方法
	 */
	@OnClose
	public void onClose() {
		
		logger.info("__________ws closed________");
	}

	/**
	 * 收到客户端消息后调用的方法
	 * 
	 * @param message
	 *            客户端发送过来的消息
	 * @param session
	 *            可选的参数
	 */
	@OnMessage
	public void onMessage(String message, Session session) {
		logger.info("收到消息"+message);
		MsgEntity msgT=null;
		try {
			 msgT=objectMapper.readValue(message, MsgEntity.class);
		} catch (Exception e) {
			logger.error("消息格式錯誤",e);
		}
		msgT.setMsgType(getMsgType().getCode());
		msgT.setFrom(this.httpSession.getId());
		MsgProcessor.reciveMsg(msgT);
	}

	/**
	 * 发生错误时调用
	 * 
	 * @param session
	 * @param error
	 */
	@OnError
	public void onError(Session session, Throwable error) {
	}

	public void sendMessage(MsgEntity msg) {
		try {
			session.getBasicRemote().sendText(objectMapper.writeValueAsString(msg));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Session getSession() {
		return session;
	}

	
	
	
	public void setSession(Session session) {
		this.session = session;
	}
	
	
	public abstract MsgTypeEnum  getMsgType();


}
