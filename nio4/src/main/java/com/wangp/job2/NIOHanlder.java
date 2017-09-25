package com.wangp.job2;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;

public class NIOHanlder {

	private SocketChannel socketChannel;

	private Selector selector;

	private ByteBuffer readByteBuffer;

	private int lasMsgEndPosition = 0;

	private final SelectionKey selectionKey;

	public NIOHanlder(SocketChannel socketChannel, Selector selector) throws IOException {
		super();
		this.socketChannel = socketChannel;
		this.selector = selector;
		socketChannel.configureBlocking(false);
		selectionKey = socketChannel.register(selector, SelectionKey.OP_READ, this);
		
		
		socketChannel.write(ByteBuffer.wrap("Welcome,My Dear boy\r\n".getBytes()));

		readByteBuffer = ByteBuffer.allocate(1000);
	}

	public void hanldIO() throws IOException {

		if (selectionKey.isReadable()) {

			socketChannel.read(readByteBuffer);
			int position = readByteBuffer.position();
			boolean readLine = false;

			for (int i = lasMsgEndPosition; i < position; i++) {

				if (readByteBuffer.get(i) == 13) {
					int msgEndPosition = i;
					byte[] msg = new byte[msgEndPosition - lasMsgEndPosition];
					readByteBuffer.position(lasMsgEndPosition);
					readByteBuffer.get(msg);
					processReadDate(msg);
					readByteBuffer.position(msgEndPosition+2);
					readLine = true;
				}
			}
		
			if (readLine) {
				readByteBuffer.limit(position);
				readByteBuffer.compact();
				lasMsgEndPosition=0;

			}
		}

	}

	private void processReadDate(byte[] msg) {
		System.out.println("rec msg:" + new String(msg));
		
		String msgStr=new   String(msg);
		
		if(msgStr.contains("download")) {
			
			String filePath=msgStr.replace("download", "").trim();
			
			
		}
		
		
		
		
		
		
	}

	public static void main(String[] s) {

		byte[] a = "\r".getBytes();

		System.out.println(a.length);
	}

}
