package com.study.nio;

import java.nio.channels.SocketChannel;

public class UserSession {
	
	
	private SocketChannel frontSocket;
	
	private SocketChannel backendSocket;

	public SocketChannel getFrontSocket() {
		return frontSocket;
	}

	public void setFrontSocket(SocketChannel frontSocket) {
		this.frontSocket = frontSocket;
	}

	public SocketChannel getBackendSocket() {
		return backendSocket;
	}

	public void setBackendSocket(SocketChannel backendSocket) {
		this.backendSocket = backendSocket;
	}
	
	

}
