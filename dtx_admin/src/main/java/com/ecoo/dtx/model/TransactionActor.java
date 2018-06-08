package com.ecoo.dtx.model;

public class TransactionActor {

	private String tranId;

	private int status;

	private int role;

	private String model;

	private String className;

	private String method;
	
	
	private String requestServiceId;
	
	private String condition;
	
	private String dispatcher;

	private String param;
	
	
	

	public String getRequestServiceId() {
		return requestServiceId;
	}

	public void setRequestServiceId(String requestServiceId) {
		this.requestServiceId = requestServiceId;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

	public String getDispatcher() {
		return dispatcher;
	}

	public void setDispatcher(String dispatcher) {
		this.dispatcher = dispatcher;
	}

	public String getTranId() {
		return tranId;
	}

	public void setTranId(String tranId) {
		this.tranId = tranId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

}
