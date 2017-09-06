package com.study.nio;

import java.io.IOException;
import java.lang.reflect.Executable;
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
import java.util.logging.LogManager;
import java.util.logging.Logger;

import com.study.nio.util.ByteBufferUtil;

public class NioServer {

	public static Logger log = Logger.getLogger(NioServer.class.getName());


	

	public void init() throws IOException {
		
		 ExecutorService es=Executors.newFixedThreadPool(3);

		ServerSocketChannel server = ServerSocketChannel.open();
		server.configureBlocking(false);
		server.bind(new InetSocketAddress("localhost", 8888));

		Selector selector = Selector.open();

		server.register(selector, SelectionKey.OP_ACCEPT);

		while (true) {
			selector.select();

			Iterator<SelectionKey> selKeys = selector.selectedKeys().iterator();

			while (selKeys.hasNext()) {

				SelectionKey seleKey = selKeys.next();
				selKeys.remove();
				if (seleKey.isAcceptable()) {
					SocketChannel sockCh = server.accept();
					es.execute(new NioReactorThread(sockCh) );
				}

			}
		}

	}

	


	public static void main(String[] args) throws IOException {
		NioServer ser = new NioServer();
		ser.init();
	}

}
