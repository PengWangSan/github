package com.ecoo.dtx.model;

import java.io.Serializable;
import java.util.Date;

public class DtxTransactionActor implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2770952350794072639L;

	private String id;

	private String tranId;

	private String model;

	private String className;

	private String method;

	private String param;

	private Integer retryCount;

	private Integer status;

	private Date createTime;

	private Date updateTime;

	public String getTranId() {
		return tranId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Integer getRetryCount() {
		return retryCount;
	}

	public void setRetryCount(Integer retryCount) {
		this.retryCount = retryCount;
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