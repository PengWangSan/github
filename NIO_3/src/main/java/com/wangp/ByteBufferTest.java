package com.wangp;

import java.nio.ByteBuffer;

public class ByteBufferTest {

	public static void testAllocte() {
		System.out.println(Runtime.getRuntime().freeMemory() / 1024 / 1024);

		ByteBuffer buffer = ByteBuffer.allocate(20 * 1024 * 1024);

		System.out.println(Runtime.getRuntime().freeMemory() / 1024 / 1024);

		ByteBuffer directBuffer = ByteBuffer.allocateDirect(1024 * 1024 * 1024);

		System.out.println(Runtime.getRuntime().freeMemory() / 1024 / 1024);
	}

	public static void testPosition() {

		ByteBuffer buffer = ByteBuffer.allocate(100);

		System.out.println("--------Test reset----------");
		buffer.clear();
		buffer.position(5);
		buffer.mark();
		buffer.position(10);
		System.out.println("before reset:" + buffer);
		buffer.reset();
		System.out.println("after reset:" + buffer);

		System.out.println("--------Test rewind--------");
		buffer.clear();
		buffer.position(10);
		buffer.limit(15);
		System.out.println("before rewind:" + buffer);
		buffer.rewind();
		System.out.println("before rewind:" + buffer);
		
		
	    System.out.println("--------Test compact--------");  
	    buffer.clear();  
	    buffer.put("abcd".getBytes());  
	    System.out.println("before compact:" + buffer);  
	    System.out.println(new String(buffer.array()));  
	    buffer.flip();  
	    System.out.println("after flip:" + buffer);  
	    System.out.println((char) buffer.get());  
	    System.out.println((char) buffer.get());  
	    System.out.println((char) buffer.get());  
	    System.out.println("after three gets:" + buffer);  
	    System.out.println("\t" + new String(buffer.array()));  
	    buffer.compact();  
	    System.out.println("after compact:" + buffer);  
	    System.out.println("\t" + new String(buffer.array()));  
	    
	    
	    
	    
	    
	    
	    
	    System.out.println("------Test get-------------");  
	    buffer = ByteBuffer.allocate(32);  
	    buffer.put((byte) 'a').put((byte) 'b').put((byte) 'c').put((byte) 'd')  
	            .put((byte) 'e').put((byte) 'f');  
	    System.out.println("before flip()" + buffer);  
	    // ת��Ϊ��ȡģʽ  
	    buffer.flip();  
	    System.out.println("before get():" + buffer);  
	    System.out.println((char) buffer.get());  
	    System.out.println("after get():" + buffer);  
	    // get(index)��Ӱ��position��ֵ  
	    System.out.println((char) buffer.get(2));  
	    System.out.println("after get(index):" + buffer);  
	    byte[] dst = new byte[10];  
	    buffer.get(dst, 0, 2);  
	    System.out.println("after get(dst, 0, 2):" + buffer);  
	    System.out.println("\t dst:" + new String(dst));  
	    System.out.println("buffer now is:" + buffer);  
	    System.out.println("\t" + new String(buffer.array()));  
	    
	    

	    System.out.println("--------Test put-------");  
	    ByteBuffer bb = ByteBuffer.allocate(32);  
	    System.out.println("before put(byte):" + bb);  
	    System.out.println("after put(byte):" + bb.put((byte) 'z'));  
	    System.out.println("\t" + bb.put(2, (byte) 'c'));  
	    // put(2,(byte) 'c')���ı�position��λ��  
	    System.out.println("after put(2,(byte) 'c'):" + bb);  
	    System.out.println("\t" + new String(bb.array()));  
	    // �����buffer�� abcdef[pos=3 lim=6 cap=32]  
	    bb.put(buffer);  
	    System.out.println("after put(buffer):" + bb);  
	    
	    System.out.println("\t" + new String(bb.array()));  
	    bb.put("feg".getBytes(),0,2);  
	    System.out.println("after put(byte[],0,2)):" + bb);  
	    
	    System.out.println("\t" + new String(bb.array()));  
	}

	public static void main(String[] args) throws InterruptedException {
		testPosition();
	}

}
