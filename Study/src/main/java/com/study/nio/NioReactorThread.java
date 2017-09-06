package com.study.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.logging.Logger;

public class NioReactorThread extends Thread {

	public static Logger log = Logger.getLogger(NioReactorThread.class.getName());

	public static byte[] content = null;

	ByteBuffer byteBuffer = ByteBuffer.allocate(1000);

	private SocketChannel sockCh;
	
	
	private boolean writeFlag=true;

	public NioReactorThread(SocketChannel SocketChannel)  {

		this.sockCh = SocketChannel;
	}

	@Override
	public void run() {
		try {
			doAccept();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private void doAccept() throws IOException {
		System.out.println("___Acc");
		Selector selector1 = Selector.open();

		sockCh.configureBlocking(false);
		sockCh.register(selector1, sockCh.validOps());

		while (!sockCh.finishConnect()) {
			System.out.println("前端连接未完成");
		}

		BackendSocket bac = new BackendSocket();
		bac.start();
		while (!bac.isConnect()) {
			System.out.println("后端连接未完成");
		}

		while (true) {
			selector1.select();

			Iterator<SelectionKey> selKeysIt = selector1.selectedKeys().iterator();
			while (selKeysIt.hasNext()) {

				SelectionKey selKey1 = selKeysIt.next();
				selKeysIt.remove();

				if (selKey1.isConnectable()) {
					log.info("___Con");

				}

				if (selKey1.isReadable()&&!writeFlag) {

					int i = 0;
					byteBuffer.clear();
//					do {
						i = sockCh.read(byteBuffer);
//					} while (i > 0);

					byte[] content = new byte[byteBuffer.position()];
					byteBuffer.flip();
					byteBuffer.get(content);

					if (content != null && content.length > 0) {
						log.info("从mysqlClient取到数据" + new String(content));
						bac.write(content);
						writeFlag=true;
					}

				}

				if (selKey1.isWritable()&&writeFlag) {//&&writeFlag

					if (content != null && content.length > 0) {

						log.info("向mysqlClient写入数据" + new String(content));

						ByteBuffer byteBuffer = ByteBuffer.wrap(content);
						while (byteBuffer.hasRemaining()) {
							sockCh.write(byteBuffer);
							writeFlag=false;
						}
						content = null;
					}

				}

			}

		}
	}
}