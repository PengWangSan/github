package com.ecoo.dtx.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class DtxTransaction implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8265482987122775852L;

	private String tranId;

	private String model;

	private String className;

	private String method;

	private String param;

	private Integer status;

	private Date createTime;

	private Date updateTime;

	private List<DtxTransactionActor> transactionActors;

	public List<DtxTransactionActor> getTransactionActors() {
		return transactionActors;
	}

	public void setTransactionActors(List<DtxTransactionActor> transactionActors) {
		this.transactionActors = transactionActors;
	}

	public String getTranId() {
		return tranId;
	}

	public void setTranId(String tranId) {
		this.tranId = tranId == null ? null : tranId.trim();
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model == null ? null : model.trim();
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className == null ? null : className.trim();
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method == null ? null : method.trim();
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param == null ? null : param.trim();
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
}