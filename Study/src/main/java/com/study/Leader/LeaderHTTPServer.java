package com.study.Leader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class LeaderHTTPServer {

	public static void main(String[] args) {
		try {
			ServerSocket serverSocket = new ServerSocket(80);
			while (true) {

				Socket socket = serverSocket.accept();
				System.out.println("Request: " + socket.toString() + " connected");
				LineNumberReader in = new LineNumberReader(new InputStreamReader(socket.getInputStream()));
				String lineInput;
				String requestPage = null;
				String userInf=null;
				while ((lineInput = in.readLine()) != null) {
					System.out.println(lineInput);
					
					if (in.getLineNumber() == 1) {
						requestPage = lineInput.substring(lineInput.indexOf('/') + 1, lineInput.lastIndexOf(' '));
						System.out.println("request page :" + requestPage);
					} else {
						if(lineInput.startsWith("Cookie: "))
						{
							userInf=lineInput;	
							System.out.println("new User "+lineInput);
						}
						else if (lineInput.isEmpty()) {
							System.out.println("header finished");
							doResponseGet(userInf,requestPage, socket);
													}
					}

				}
			}

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	private static void doResponseGet(String userInf,String requestPage,Socket socket) throws IOException {
		final String WEB_ROOT = "c:";
		File theFile = new File(WEB_ROOT, requestPage);
		OutputStream out=socket.getOutputStream();
		if (theFile.exists()) {
			// 浠庢湇鍔″櫒鏍圭洰褰曚笅鎵惧埌鐢ㄦ埛璇锋眰鐨勬枃浠跺苟鍙戦�佸洖娴忚鍣�
			InputStream fileIn = new FileInputStream(theFile);
			byte[] buf = new byte[fileIn.available()];
			fileIn.read(buf);
			fileIn.close();
			out.write(buf);
			out.flush();
			socket.close();
			System.out.println("request complete.");
		} else {
			
			String msg=" I can't find bao zang...cry.. \r\n"+((userInf==null)?" new user ...":userInf);
			String response = "HTTP/1.1 200 OK\r\n";
			response +="Server: Leader Server/0.1\r\n";
			if(userInf==null)
			{
				response+=genCookieHeader();	
			}
			response +="Content-Length: "+msg.length()+"\r\n";
			response += "\r\n";
			response+=msg;
			out.write(response.getBytes());
			out.flush();
		
		}

	}
	private static String genCookieHeader()
	{
		String header="Set-Cookie: jsessionid="+System.currentTimeMillis()+".leaderus; domain=localhost"+"\r\n";
		header+="Set-Cookie: autologin=true; domain=localhost"+"\r\n";
		return header;
	}

}
