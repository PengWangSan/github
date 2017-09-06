package com.study.nio;

import java.io.Closeable;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.logging.Logger;

import com.study.nio.util.ByteBufferUtil;

public class BackendSocket extends Thread {

	public static Logger log = Logger.getLogger(BackendSocket.class.getName());

	public SocketChannel socketChannel;

	private Selector selector;

	private ByteBuffer byteBuffer;

	private byte[] content = null;

	public void write(byte[] content) {

		this.content = content;
	}

	public boolean isConnect() throws IOException {
		return socketChannel.finishConnect();

	}

	@Override
	public void run() {
		init();

	}

	public BackendSocket() throws IOException {
		socketChannel = SocketChannel.open();
		selector = Selector.open();
		byteBuffer = ByteBuffer.allocate(1000);
		String backendServer = "192.168.1.6";
		int port = 3306;
		socketChannel.configureBlocking(false);
		socketChannel.connect(new InetSocketAddress(backendServer, port));

		socketChannel.register(selector, socketChannel.validOps());

	}

	public void init() {

		while (true) {

			try {
				listenSocket();
			} catch (IOException e) {
				
				System.exit(0);
			}
		}

	}

	private void listenSocket() throws IOException {
		selector.select();

		Iterator<SelectionKey> selectKeyIt = selector.selectedKeys().iterator();

		while (selectKeyIt.hasNext()) {

			SelectionKey selKey = selectKeyIt.next();

			selectKeyIt.remove();
			if (selKey.isConnectable()) {
				
				System.out.println("__CON");
				if(socketChannel.isConnectionPending()) {
					socketChannel.finishConnect();
				}
			}
			if (selKey.isReadable()) {

				byteBuffer.clear();
				int i = 0;

//				do {
					i = socketChannel.read(byteBuffer);
//				} while (i > 0);
//
//				if (i == -1) {
//					closeQuietly(socketChannel);
//				}

				byte[] content = new byte[byteBuffer.position()];
				byteBuffer.flip();
				byteBuffer.get(content);
				if (content != null && content.length > 0) {
					log.info("从mysqlServer服务端读取到数据" + new String(content));
					NioReactorThread.content = content;
				}
			}
			if (selKey.isWritable()) {

				if (content != null && content.length > 0) {

					log.info("向mysqlServer写入数据" + new String(content));

					ByteBuffer byteBuffer = ByteBuffer.wrap(content);
					while (byteBuffer.hasRemaining()) {
						socketChannel.write(byteBuffer);
					}
					content = null;

				}

			}
		

		}

	}
	
	public static void closeQuietly(final Closeable closeable) {
		try {
			if (closeable != null) {
				closeable.close();
			}
		} catch (IOException e) {
		}
	}

	public static void main(String[] args) throws IOException {

		BackendSocket bs = new BackendSocket();

		bs.init();
	}

}
