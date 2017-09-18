package com.wangp;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Set;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NIOReactor extends Thread {

	public static Logger log = LoggerFactory.getLogger(NIOReactor.class);

	private Selector selector;

	public static ExecutorService es = Executors.newFixedThreadPool(4);

	public NIOReactor() {
		super();
		try {
			this.selector = Selector.open();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void register(SocketChannel socketChannel, int selKey, Object o) {

		try {
			socketChannel.register(selector, selKey, o);
		} catch (ClosedChannelException e) {
			e.printStackTrace();
		}

	}

	public void run() {

		while (true) {
			try {

				log.info("Before select");
				selector.select();
				log.info("After select");
				Set<SelectionKey> selKeys = selector.selectedKeys();
				for (SelectionKey selKey : selKeys) {

					SocketChannel socketChannel = (SocketChannel) selKey.channel();
					if (selKey.isReadable()) {

						ByteBuffer byteBuffer = ByteBuffer.allocate(100);
						socketChannel.read(byteBuffer);

						es.submit(new NIOHanlder(socketChannel, this, byteBuffer));
						
						selKey.interestOps(SelectionKey.OP_WRITE);

					} else if (selKey.isWritable()) {

						log.info("recive write event");

						ByteBuffer byteBuffer = (ByteBuffer) selKey.attachment();
						if (byteBuffer != null) {
							
							log.info(new String(byteBuffer.array()));
							
							int write = socketChannel.write(byteBuffer);

							log.info("write:" + write + "remain:" + byteBuffer.remaining());

							if (byteBuffer.hasRemaining()) {

								byteBuffer = byteBuffer.compact();
								selKey.attach(byteBuffer);
							} else {
								selKey.interestOps(SelectionKey.OP_READ);
							}
						}

					}

				}
				selKeys.clear();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
