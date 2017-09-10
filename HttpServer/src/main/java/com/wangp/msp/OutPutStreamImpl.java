package com.wangp.msp;

import java.io.IOException;
import java.net.Socket;

import com.wangp.msp.interfaces.OutPutStream;

public class OutPutStreamImpl implements OutPutStream {

	private Socket socket;

	public OutPutStreamImpl(Socket socket) {
		super();
		this.socket = socket;
	}

	public void println(String str) {

		try {
			socket.getOutputStream().write((str + Constant.SEPARATOR).getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void print(String str) {
		try {
			socket.getOutputStream().write((str).getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
