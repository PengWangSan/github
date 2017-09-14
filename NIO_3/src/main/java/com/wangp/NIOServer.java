package com.wangp;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;

public class NIOServer {

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
					socketChannel.register(selecter, SelectionKey.OP_READ);

				} else if (selectionKey.isReadable()) {


					SocketChannel channel = (SocketChannel) selectionKey.channel();

					ByteBuffer byteBuffer = ByteBuffer.allocate(50);

					// byteBuffer.put("\r\n Follow you:".getBytes());

					//
					// for(int i=0;i<byteBuffer.capacity();i++) {
					// byteBuffer.put((byte)('a'));
					// }

					int read = channel.read(byteBuffer);

					System.out.println(read + ":" + new String(byteBuffer.array()));
					if (read < byteBuffer.capacity() && read > 0) {
						System.out.println(":数据已经读取完全");
						selectionKey.interestOps(selectionKey.interestOps()|SelectionKey.OP_WRITE);
					}

				} else if (selectionKey.isWritable()) {

					
					System.out.println("recive write event");
					
					SocketChannel channel = (SocketChannel) selectionKey.channel();

					ByteBuffer existbyteBuffer = (ByteBuffer) selectionKey.attachment();

					if (existbyteBuffer != null && existbyteBuffer.hasRemaining()) {
						int write=channel.write(existbyteBuffer);
						
						System.out.println("Write exist"+write);
						
					
						System.out.println("Not Write Finised,bind session,reamins"+existbyteBuffer.remaining());
						if(existbyteBuffer.hasRemaining()) {
							
						}else {
							System.out.println("Finised");
							selectionKey.interestOps(selectionKey.interestOps()&~SelectionKey.OP_WRITE);
						}
						
						
					} else {
						int bufferSize = channel.socket().getSendBufferSize();

						ByteBuffer byteBuffer = ByteBuffer.allocate(200);
						for (int i = 0; i < byteBuffer.capacity()-2; i++) {
							byteBuffer.put((byte) ('a'));
						}
						byteBuffer.put("\r\n".getBytes());
						byteBuffer.flip();
						System.out.println("Send another huge buffer___"+byteBuffer.capacity());
						
						int write=channel.write(byteBuffer);
						
						System.out.println("Write"+write);
						if (byteBuffer.hasRemaining()) {
							System.out.println("Not Write Finised,bind session,reamins"+byteBuffer.remaining());
							selectionKey.attach(byteBuffer);
						}else {
							
							System.out.println("Finised");
							selectionKey.interestOps(selectionKey.interestOps()&~SelectionKey.OP_WRITE);
						}
					}

				}
				selectKeyIt.remove();

			}

		}

	}

}
