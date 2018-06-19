package com.ecoo.dtx.admin.ws.dto;

public class MsgEntity {
	
	
	
	private String from;
	
	private String to;
	
	private String fromName;
	
	
	private String msgBody;
	
	
	private int msgType;
	
	
	private long time;


	public int getMsgType() {
		return msgType;
	}

	
	

	public String getFromName() {
		return fromName;
	}




	public void setFromName(String fromName) {
		this.fromName = fromName;
	}




	public void setMsgType(int msgType) {
		this.msgType = msgType;
	}


	public String getFrom() {
		return from;
	}


	public void setFrom(String from) {
		this.from = from;
	}


	public String getTo() {
		return to;
	}


	public void setTo(String to) {
		this.to = to;
	}


	public String getMsgBody() {
		return msgBody;
	}


	public void setMsgBody(String msgBody) {
		this.msgBody = msgBody;
	}


	public long getTime() {
		return time;
	}


	public void setTime(long time) {
		this.time = time;
	}
	
	
	
	
	
	
	
	

}
