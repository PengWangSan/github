package com.ecoo.dtx.admin.ws.cmd;

import org.springframework.util.StringUtils;

import com.ecoo.dtx.admin.ws.SocketHolder;
import com.ecoo.dtx.admin.ws.WebSocket;
import com.ecoo.dtx.admin.ws.dto.MsgEntity;

public class MsgProcessor {

	public static void reciveMsg(MsgEntity msgE) {

		if (StringUtils.isEmpty(msgE.getTo())) {//没有收件人的消息，进行群发
			for (WebSocket socket : SocketHolder.getSockets().values()) {
				if (socket.getMsgType().getCode() != msgE.getMsgType()) {
					socket.sendMessage(msgE);
				}
			}
		} else {
			SocketHolder.getSockets().get(msgE.getTo()).sendMessage(msgE);
		}

	}

}
