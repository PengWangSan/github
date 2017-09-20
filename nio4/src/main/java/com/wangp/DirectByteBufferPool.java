package com.wangp;

import java.nio.ByteBuffer;
import java.util.TreeSet;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DirectByteBufferPool {

	private TreeSet<PoolByteBuffer> byteBuffers;

	private int poolSize;

	private Lock lock = new ReentrantLock();

	public DirectByteBufferPool() {
		
		byteBuffers=new TreeSet<PoolByteBuffer>();
		
		byteBuffers.comparator();
		
		for (int i = 0; i < poolSize; i++) {
			ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024 * i);
			PoolByteBuffer poolByteBuffer = new PoolByteBuffer(byteBuffer);
			byteBuffers.add(poolByteBuffer);
		}

	}

	public ByteBuffer allocate(int capacity) {

		
		PoolByteBuffer poolBuffer=byteBuffers.higher(new PoolByteBuffer(capacity));
		
		return null;

	}

	public class PoolByteBuffer implements Comparable<PoolByteBuffer> {

		private ByteBuffer byteBuffer;

		private boolean used = false;
		
		private int bufferSize;
		
		
		

		public PoolByteBuffer(int bufferSize) {
			super();
			this.bufferSize = bufferSize;
		}



		public PoolByteBuffer(ByteBuffer byteBuffer) {
			super();
			this.byteBuffer = byteBuffer;
			bufferSize=byteBuffer.capacity();
		}

		
		
		public int getBufferSize() {
			return bufferSize;
		}



		public void setBufferSize(int bufferSize) {
			this.bufferSize = bufferSize;
		}



		public ByteBuffer getByteBuffer() {
			return byteBuffer;
		}

		public void setByteBuffer(ByteBuffer byteBuffer) {
			this.byteBuffer = byteBuffer;
		}

		public boolean isUsed() {
			return used;
		}

		public void setUsed(boolean used) {
			this.used = used;
		}

		public int compareTo(PoolByteBuffer o) {
			
			int result=0;
			if(bufferSize>o.bufferSize) {
				result=-1;
			}else if(bufferSize<o.bufferSize) {
				result=-1;		
			}
			
			return result;
		}

	}

}
