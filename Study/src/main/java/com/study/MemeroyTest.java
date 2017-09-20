package com.study;

import java.nio.ByteBuffer;

public class MemeroyTest {

	
	
	
	public static void main(String[] args) {
		
		
		System.out.println(getFree());
		
		
	
		
		long time=System.currentTimeMillis();
		ByteBuffer.allocateDirect(1024*1024*20);
		long time1=System.currentTimeMillis();
		
		System.out.println("allocate direct:"+(time1-time));
		
		 time=System.currentTimeMillis();
		ByteBuffer.allocate(1024*1024*20);
		time1=System.currentTimeMillis();
		System.out.println("allocate :"+(time1-time));
		
		System.out.println(getFree());
		
		System.gc();
	}
	
	public static String getFree() {

		long time=System.currentTimeMillis();
		long free=Runtime.getRuntime().freeMemory();
		long time1=System.currentTimeMillis();
		System.out.println("getFree:"+(time1-time));
		return	free/1024/1024+"M";
	}
	
	
}
