package com.wangp.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

import com.wangp.msp.MspParse;
import com.wangp.msp.MspPervlet;
import com.wangp.msp.OutPutStreamImpl;
import com.wangp.msp.PvRequestImpl;
import com.wangp.msp.PvResponseImpl;

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
 
				List<String>  reqStr=new ArrayList<String>();
				while ((lineStr = lineReader.readLine()) != null) {

				 	System.out.println(lineStr);
					reqStr.add(lineStr);
					
					if (lineReader.getLineNumber() == 1) {
						requestPage = lineStr.substring(lineStr.indexOf("/") + 1, lineStr.lastIndexOf(" "));

						if(requestPage.indexOf("?")!=-1) {
					     	requestPage=requestPage.substring(0,requestPage.indexOf("?"));
						}
						
					} else {
						if (lineStr.isEmpty()) {
							System.out.println("Head Finished");
							doResponse(requestPage,reqStr);
						}
						break;
					}

			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("___ÍË³ö¾€³Ì");
	}

	private void doResponse(String requestPage,List<String>  reqStr) throws IOException, InstantiationException, IllegalAccessException {

		String webRoot = this.getClass().getClassLoader().getResource("").getPath();
		File file = new File(webRoot, requestPage);

		OutputStream out = socket.getOutputStream();

		if (file.exists() && file.isFile()) {

			MspParse mspParse = new MspParse();
			Class<? extends MspPervlet> c = mspParse.parse(file);

			MspPervlet pervlet = c.newInstance();
			pervlet.setRequest(new PvRequestImpl(socket,reqStr));
			pervlet.setResponse(new PvResponseImpl(socket));
			pervlet.setOut(new OutPutStreamImpl(socket));
			pervlet.handlerRequest();
			
			out.close();

		} else {
			String msg = "12345678";

			String res = "HTTP/1.1 200 OK\r\n";
			res += "Server: nginx\r\n";
			res += "Content-Length:" + (msg.length() - 1) + "\r\n";
			res += "\r\n";

			res += msg;
			System.out.println(res);
			out.write(res.getBytes());
			out.flush();
			// out.close();
		}

	}

}
