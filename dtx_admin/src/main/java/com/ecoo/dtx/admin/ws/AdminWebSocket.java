package com.ecoo.dtx.admin.ws;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.servlet.http.HttpSession;
import javax.websocket.EndpointConfig;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.ecoo.dtx.admin.config.GetHttpSessionConfigurator;

@ServerEndpoint(value="/admin/websocket",configurator=GetHttpSessionConfigurator.class)
@Component
public class AdminWebSocket {

	public Logger logger = LoggerFactory.getLogger(AdminWebSocket.class);

	private Session session;
	
	public static Map<String,AdminWebSocket> sockets=new HashMap<>();

	@OnOpen
	public void onOpen(Session session, EndpointConfig config) {

		logger.info("————————————连接开始");
		
		HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
		
		this.session=session;
		sockets.put(httpSession.getId(), this);
	}

	/**
	 * 连接关闭调用的方法
	 */
	@OnClose
	public void onClose(Session session) {
		
//		sockets.remove(session.getId());
		
		logger.info("————————————关闭：" );
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

		logger.info("————————————消息：" + message);

		try {
			session.getBasicRemote().sendText("OK+++");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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

	public Session getSession() {
		return session;
	}

	public void setSession(Session session) {
		this.session = session;
	}

	public static Map<String, AdminWebSocket> getSockets() {
		return sockets;
	}

	public static void setSockets(Map<String, AdminWebSocket> sockets) {
		AdminWebSocket.sockets = sockets;
	}
	
	
	
}
