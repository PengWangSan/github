package com.wangp.msp;

import com.wangp.msp.interfaces.OutPutStream;
import com.wangp.msp.interfaces.Pervlet;
import com.wangp.msp.interfaces.PvReponse;
import com.wangp.msp.interfaces.PvRequest;

public class MspPervlet implements Pervlet {
	
	
	public PvRequest request;
	
	public PvReponse response;
	
	
	public OutPutStream out;
	

	public MspPervlet() {
		super();
	}

	public MspPervlet(PvRequest req, PvReponse res, OutPutStream out) {
		super();
		this.request = req;
		this.response = res;
		this.out = out;
	}

	public void handlerRequest() {

	}

	public PvRequest getRequest() {
		return request;
	}

	public void setRequest(PvRequest request) {
		this.request = request;
	}

	public PvReponse getResponse() {
		return response;
	}

	public void setResponse(PvReponse response) {
		this.response = response;
	}

	public OutPutStream getOut() {
		return out;
	}

	public void setOut(OutPutStream out) {
		this.out = out;
	}
	
	

}
