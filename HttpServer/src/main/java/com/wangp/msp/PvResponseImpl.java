package com.wangp.msp;

import java.net.Socket;

import com.wangp.msp.interfaces.OutPutStream;
import com.wangp.msp.interfaces.PvReponse;
import com.wangp.msp.interfaces.PvRequest;

public class PvResponseImpl implements PvReponse {
	
	
	
	private Socket socket;
	
	
	

	public PvResponseImpl(Socket socket) {
		super();
		this.socket = socket;
	}




	public OutPutStream getOut() {
		return new OutPutStreamImpl(socket);
	}


}
