package com.wangp;

import java.io.IOException;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class NIOHanlder implements Runnable {
	
	
	public static Logger log=LoggerFactory.getLogger(NIOHanlder.class);

	private SocketChannel sockectChannel;
	
	
	private NIOReactor reactor;
	
	private ByteBuffer byteBuffer;
	
	
	

	




	public NIOHanlder(SocketChannel sockectChannel, NIOReactor reactor, ByteBuffer byteBuffer) {
		super();
		this.sockectChannel = sockectChannel;
		this.reactor = reactor;
		this.byteBuffer = byteBuffer;
	}









	public void run() {

		log.info("recive read event"+new String(byteBuffer.array()));
		
		
		
		try {
//			int dataLen=sockectChannel.socket().getSendBufferSize()*5;
			int dataLen=22;
			ByteBuffer byteBuffer=ByteBuffer.allocate(dataLen);
			
			for(int i=0;i<dataLen-2;i++) {
				byteBuffer.put((byte)('a'+i%25));
				
			}
			byteBuffer.flip();
			
			reactor.register(sockectChannel, SelectionKey.OP_WRITE, byteBuffer);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
