package com.study.nio.util;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class ByteBufferUtil {

	public static String getString(ByteBuffer buffer) {
		buffer.flip();
		Charset charset = null;
		CharsetDecoder decoder = null;
		CharBuffer charBuffer = null;
		try {
			charset = Charset.forName("GBK");
			decoder = charset.newDecoder();
			charBuffer = decoder.decode(buffer.asReadOnlyBuffer());
			return charBuffer.toString();
		} catch (Exception ex) {
			ex.printStackTrace();
			return "error";
		}
	}

	public static ByteBuffer transferString(String str) {
	
		return ByteBuffer.wrap(str.getBytes());
	}
	
	
	public static void main(String[] args) {
		ByteBuffer by=ByteBuffer.allocate(100);
		by.put("Sdsf".getBytes());
		System.out.println(getString(by));
		
	}

}
