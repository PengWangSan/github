package com.wangp.job2;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class TelnetServer {
	
	
	
	  public void init() throws IOException {
		  
		  ServerSocketChannel server=ServerSocketChannel.open();
		  server.bind(new InetSocketAddress(8080));
		  server.configureBlocking(false);
		  
		  Selector selector=Selector.open();
		  
		  server.register(selector, SelectionKey.OP_ACCEPT);
		  
		  
		  while(true) {
			  
			  selector.select();
			  
			  Set<SelectionKey> keys=selector.selectedKeys();
			  for(SelectionKey key:keys) {
				  
				  if(key.isAcceptable()) {
					  
					 SocketChannel socketChannel= server.accept();
					 new NIOHanlder(socketChannel, selector);
				  }else {
					  NIOHanlder nioHanlder= (NIOHanlder) key.attachment();
					  nioHanlder.hanldIO();
				  }
				  
				  
				  
			  }
			  keys.clear();
		  }
		  
	  }
	
	
	 
	  
	  public static void main(String[] args) throws IOException {
		  
		  TelnetServer server=new TelnetServer();
		  server.init();
		  
	  }
	
	
	
	
	

}
