package com.study.Leader.nio;

import java.io.IOException;

public class MainServer {

	public static void main(String[] args) throws IOException {
		MyNIORector reacot=new MyNIORector(9000);
		reacot.start();
	}

}
