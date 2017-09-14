package com.wangp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;

public class NIOServer {
	
	
	
	
	
	
	public void init() throws IOException {
		
		ServerSocketChannel serverChannel=ServerSocketChannel.open();
		InetSocketAddress local=new InetSocketAddress(8080);
		serverChannel.bind(local);
		serverChannel.configureBlocking(false);
		
		Selector selecter=Selector.open();
		
		serverChannel.register(selecter, SelectionKey.OP_ACCEPT);
		while(true) {
			selecter.select();
			
			Iterator<SelectionKey> selectKeyIt=selecter.selectedKeys().iterator();
			
			while(selectKeyIt.hasNext()) {
				
				SelectionKey selectionKey=selectKeyIt.next();
				if(selectionKey.isAcceptable()) {
					
				}else {
					
					
					
				}
				
			}
			
			
		
		}
		
		
		
	}
	

}
