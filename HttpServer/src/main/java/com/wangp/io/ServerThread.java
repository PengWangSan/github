package com.wangp.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.net.Socket;

public class ServerThread implements Runnable {

	private Socket socket;

	public ServerThread(Socket socket) {
		this.socket = socket;
	}

	public void run() {

		try {
			InputStreamReader reader = new InputStreamReader(socket.getInputStream());

			LineNumberReader lineReader = new LineNumberReader(reader);

			String lineStr = null;

			String requestPage = null;
			while (true) {
				
				while ((lineStr = lineReader.readLine()) != null) {

					System.out.println(lineStr);
					if (lineReader.getLineNumber() == 1) {

						requestPage = lineStr.substring(lineStr.indexOf("/") + 1, lineStr.lastIndexOf(" "));

					} else {
						if (lineStr.isEmpty()) {
							System.out.println("Head Finished");
							doResponse(requestPage);
							break;
						}

					}

				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void doResponse(String requestPage) throws IOException {

		String webRoot = "D:";
		File file = new File(webRoot, requestPage);

		OutputStream out = socket.getOutputStream();

		if (file.exists()&&file.isFile()) {


		} else {
			String msg = "12345678";

			String res = "HTTP/1.1 200 OK\r\n";
			res += "Server: nginx\r\n";
			res += "Content-Length:" + (msg.length()-1) + "\r\n";
			res += "\r\n";

			res += msg;
			System.out.println(res);
			out.write(res.getBytes());
			out.flush();
			// out.close();
		}

	}

}
