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

@ServerEndpoint(value="/websocket",configurator=GetHttpSessionConfigurator.class)
@Component
public class WebSocket {

	public Logger logger = LoggerFactory.getLogger(WebSocket.class);

	private Session session;
	
	public static Map<String,WebSocket> sockets=new HashMap<>();

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
	public void onClose() {
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

	public static Map<String, WebSocket> getSockets() {
		return sockets;
	}

	public static void setSockets(Map<String, WebSocket> sockets) {
		WebSocket.sockets = sockets;
	}
	
	
	
}
