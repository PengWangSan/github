package com.wangp.job2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
	
	
	private FileChannel fileChannel;
	
	private long fileStartPosition;
	
	private long fileEndPosition;

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
					readByteBuffer.position(msgEndPosition + 2);
					readLine = true;
				}
			}

			if (readLine) {
				readByteBuffer.limit(position);
				readByteBuffer.compact();
				lasMsgEndPosition = 0;

			}
		}else if(selectionKey.isWritable()) {
			
			
			
			fileStartPosition=fileEndPosition;
			long length=fileChannel.transferTo(fileStartPosition, fileChannel.size(), socketChannel);
			fileEndPosition=fileStartPosition+length-1;
			if(fileEndPosition==fileChannel.size()-1) {
				
				
				System.out.println("__End");
				
				selectionKey.interestOps(selectionKey.interestOps()&~SelectionKey.OP_WRITE);
			}
			System.out.println(fileEndPosition);
		}

	}

	private void processReadDate(byte[] msg) throws IOException {
		System.out.println("rec msg:" + new String(msg));

		String msgStr = new String(msg);

		if (msgStr.contains("download")) {

			String filePath = msgStr.replace("download", "").trim();

			File file = new File(filePath);

			if (!file.exists()) {

				socketChannel.write(ByteBuffer.wrap("File not exist\r\n".getBytes()));
				return;
			}
			if (file.isDirectory()) {
				socketChannel.write(ByteBuffer.wrap("The Path is not file\r\n".getBytes()));
				return;
			}

			fileChannel = new FileInputStream(file).getChannel();

			fileStartPosition=0;
			long length=fileChannel.transferTo(fileStartPosition, fileChannel.size(), socketChannel);
			fileEndPosition=length+fileStartPosition-1;
			if(fileEndPosition<=fileChannel.size()-fileStartPosition) {
				selectionKey.interestOps(selectionKey.interestOps()|SelectionKey.OP_WRITE);
				
			}
			
			
		

		}
		
		if(msgStr.equals("exit")) {
			socketChannel.close();
		}

	}

	public static void main(String[] s) throws IOException {

		File file = new File("D:\\soft\\G2017_02_4.txt");

		FileInputStream fis = new FileInputStream(file);
		int r = 0;

		StringBuffer sb = new StringBuffer();
		do {
			byte[] content = new byte[1000];
			r = fis.read(content);
			sb.append(new String(content));
		} while (r != -1);
		fis.close();

		System.out.println(sb);

		FileOutputStream fos = new FileOutputStream("D:/nio4.txt");

		for (int i = 1;; i++) {

			fos.write((sb.toString()+"++++"+i).getBytes());
			
			if(sb.toString().getBytes().length*i>110*1024*1024) {
				
				break;
			}
			
		}

		fos.flush();
		fos.close();
	}

}
