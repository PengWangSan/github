package com.wangp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NIOServer {
	
	public static Logger log=LoggerFactory.getLogger(NIOServer.class);
	
	
	private ExecutorService es=Executors.newFixedThreadPool(4);
	


	public static void main(String[] args) throws IOException, InterruptedException {

		NIOServer nioServer = new NIOServer();
		nioServer.init();

	}

	public void init() throws IOException, InterruptedException {

		ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
		InetSocketAddress local = new InetSocketAddress(8080);
		serverSocketChannel.bind(local);
		serverSocketChannel.configureBlocking(false);

		Selector selecter = Selector.open();

		serverSocketChannel.register(selecter, SelectionKey.OP_ACCEPT);
		while (true) {
			selecter.select();
			
		
			Iterator<SelectionKey> selectKeyIt = selecter.selectedKeys().iterator();
			while (selectKeyIt.hasNext()) {
				SelectionKey selectionKey = selectKeyIt.next();
				if (selectionKey.isAcceptable()) {
					ServerSocketChannel serverChannel = (ServerSocketChannel) selectionKey.channel();
					SocketChannel socketChannel = serverChannel.accept();
					socketChannel.configureBlocking(false);
					socketChannel.write(ByteBuffer.wrap(("Welcome,My Dear boy \r\n").getBytes()));
					NIOReactor ractor=DispatcherTool.dispatch();
					ractor.register(socketChannel,SelectionKey.OP_READ,null);
				} else {
					log.info("_select event");
					
				}
				selectKeyIt.remove();

			}

		}

	}
	
	
	private void select(Selector selecter) throws IOException {
		 Set<SelectionKey> keys = selecter.selectedKeys();
		
		for (SelectionKey selectionKey:keys) {
			if (selectionKey.isAcceptable()) {
				ServerSocketChannel serverChannel = (ServerSocketChannel) selectionKey.channel();
				SocketChannel socketChannel = serverChannel.accept();
				socketChannel.configureBlocking(false);
				socketChannel.write(ByteBuffer.wrap(("Welcome,My Dear boy \r\n").getBytes()));
				socketChannel.register(selecter, SelectionKey.OP_READ);
			} else {
			}

		}
		keys.clear();
	}

}
