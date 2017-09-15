package com.wangp;

import java.io.IOException;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SelectionKeyProcessRunnable implements Runnable{
	
	public static Logger log=LoggerFactory.getLogger(SelectionKeyProcessRunnable.class);
	
	
	private SelectionKey selKey;

	public SelectionKeyProcessRunnable(SelectionKey selKey) {
		super();
		this.selKey = selKey;
	}


	public void run()  {
		 if(selKey.isReadable()) {
			 
			processReadEvent();
			
		  }else if(selKey.isWritable()) {
			try {
				processWriteEvent();
			} catch (IOException e) {
				e.printStackTrace();
			}
		  }
	
	}
	
	
	private void processReadEvent() {
		try {
		
			SocketChannel channel = (SocketChannel) selKey.channel();
			ByteBuffer byteBuffer = ByteBuffer.allocate(50);
			log.info("recive read event:"+selKey);
			int read = channel.read(byteBuffer);
			log.info(read + ":" + new String(byteBuffer.array()));
			if (read < byteBuffer.capacity() && read > 0) {
				log.info(":数据已经读取完全");
				selKey.interestOps(selKey.interestOps()|SelectionKey.OP_WRITE);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void processWriteEvent() throws IOException {
		    log.info("recive write event");
			
			SocketChannel channel = (SocketChannel) selKey.channel();

			ByteBuffer existbyteBuffer = (ByteBuffer) selKey.attachment();

			if (existbyteBuffer != null && existbyteBuffer.hasRemaining()) {
				int write=channel.write(existbyteBuffer);
				
				log.info("Write exist"+write);
				
			
				log.info("Not Write Finised,bind session,reamins"+existbyteBuffer.remaining());
				if(existbyteBuffer.hasRemaining()) {
					
				}else {
					log.info("Finised");
					selKey.interestOps(selKey.interestOps()&~SelectionKey.OP_WRITE);
				}
				
				
			} else {
				int bufferSize = channel.socket().getSendBufferSize();

				ByteBuffer byteBuffer = ByteBuffer.allocate(200);
				for (int i = 0; i < byteBuffer.capacity()-2; i++) {
					byteBuffer.put((byte) ('a'));
				}
				byteBuffer.put("\r\n".getBytes());
				byteBuffer.flip();
				log.info("Send another huge buffer___"+byteBuffer.capacity());
				
				int write=channel.write(byteBuffer);
				
				log.info("Write"+write);
				if (byteBuffer.hasRemaining()) {
					log.info("Not Write Finised,bind session,reamins"+byteBuffer.remaining());
					selKey.attach(byteBuffer);
				}else {
					
					log.info("Finised");
					selKey.interestOps(selKey.interestOps()&~selKey.OP_WRITE);
				}
			}
	}

}
