package com.wangp;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class NIOReactor implements Runnable {

	private Selector selector;

	public NIOReactor() {
		super();
		try {
			this.selector = Selector.open();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void register(SocketChannel socketChannel) {

		try {
			socketChannel.register(selector, SelectionKey.OP_READ);
		} catch (ClosedChannelException e) {
			e.printStackTrace();
		}

	}

	public void run() {

		while (true) {
			try {
				selector.select();
				Set<SelectionKey> selKeys = selector.selectedKeys();
				for (SelectionKey selKey : selKeys) {

					SocketChannel socketChannel=(SocketChannel) selKey.channel();
					if (selKey.isReadable()) {

						
						ByteBuffer byteBuffer=ByteBuffer.allocate(100);
						socketChannel.read(byteBuffer);
						
						
						
						
					}

				}
				selKeys.clear();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
