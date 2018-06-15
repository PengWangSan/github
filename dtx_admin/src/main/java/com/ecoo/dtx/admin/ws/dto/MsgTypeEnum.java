package com.ecoo.dtx.admin.ws.dto;

public enum MsgTypeEnum {

	CLIENT(1),AMDIN(2);
	
	private int code;
	
	MsgTypeEnum(int code) {
		this.code=code;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	
	
}
