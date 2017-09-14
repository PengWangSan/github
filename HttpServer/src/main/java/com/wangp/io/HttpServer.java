package com.wangp.io;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HttpServer {

	public static void main(String[] args) {

		ExecutorService executorService = Executors.newFixedThreadPool(1);
		ServerSocket server = null;
		try {
			server = new ServerSocket(80);

			while (true) {
				Socket socket = server.accept();
				
				System.out.println("request:"+socket+"connectd");
				
//				ServerThread t=new ServerThread(socket);
				executorService.submit(new ServerThread(socket));
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				server.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
