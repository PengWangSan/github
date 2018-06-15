package com.ecoo.dtx.admin.ws;

import java.util.HashMap;
import java.util.Map;

import com.ecoo.dtx.admin.ws.dto.MsgTypeEnum;

public class SocketHolder {
	
	
	private static Map<String, WebSocket> sockets = new HashMap<>();
	
	
	
	
	
	public static Map<String, WebSocket> getClientSoockest() {
		Map<String, WebSocket> clientSockect=new HashMap<>();
		for(String sessinoId:sockets.keySet()) {
			WebSocket socket=sockets.get(sessinoId);
			if(MsgTypeEnum.CLIENT==socket.getMsgType()) {
				clientSockect.put(sessinoId, socket);
			}
			
		}
		return clientSockect;
	}
	
	
	

	public static Map<String, WebSocket> getSockets() {
		return sockets;
	}

	public static void setSockets(Map<String, WebSocket> sockets) {
		SocketHolder.sockets = sockets;
	}
	
	
	
	

}
