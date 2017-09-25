package com.wangp.job1;

import java.nio.ByteBuffer;
import java.util.TreeSet;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DirectByteBufferPool {

	private TreeSet<PoolByteBuffer> byteBuffers;

	private int poolSize;

	private Lock lock = new ReentrantLock();

	public DirectByteBufferPool(int poolSize) {
		
		byteBuffers=new TreeSet<PoolByteBuffer>();
		 
		byteBuffers.comparator();
		
		for (int i = 0; i < poolSize; i++) {
			ByteBuffer byteBuffer = ByteBuffer.allocateDirect(1024 * i);
			PoolByteBuffer poolByteBuffer = new PoolByteBuffer(byteBuffer);
			byteBuffers.add(poolByteBuffer);
		}
        this.poolSize=poolSize;
	}

	public ByteBuffer allocate(int capacity) {

		lock.lock();
		PoolByteBuffer poolBuffer=null;
		try {
			poolBuffer=byteBuffers.higher(new PoolByteBuffer(capacity));
			if(poolBuffer!=null) {
				poolBuffer.setUsed(1);
			}
		}finally {
			lock.unlock();
		}
		
		return poolBuffer!=null?poolBuffer.getByteBuffer():null;

	}

	
	public void recyle(ByteBuffer byteBuffer) {
		for(PoolByteBuffer poolByteBuffer:byteBuffers) {
			if(poolByteBuffer.getByteBuffer().equals(byteBuffer)) {
				poolByteBuffer.setUsed(0);
				byteBuffer.clear();
			}
		}
	}
	
	public static void main(String[] args) {
		
		DirectByteBufferPool pool=new DirectByteBufferPool(10);
		
		ByteBuffer s=pool.allocate(100);
		

		ByteBuffer s1=pool.allocate(100);
		
		
		pool.recyle(s);
		
		System.out.println(s.equals(s1));
		
		
		ByteBuffer s2=pool.allocate(100);
		
		System.out.println(s.equals(s2));
	}
    	
	
	
	public class PoolByteBuffer implements Comparable<PoolByteBuffer> {

		private ByteBuffer byteBuffer;

		private int used = 0;
		
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

		

		public int getUsed() {
			return used;
		}



		public void setUsed(int used) {
			this.used = used;
		}



		public int compareTo(PoolByteBuffer o) {
			
			
			if(used>o.used) {
				return -1;
			}else if(used<o.used) {
				return 1;
			}else {
				if(bufferSize>o.bufferSize) {
					return 1;
				}else if(bufferSize<o.bufferSize) {
					return -1;		
				}else {
					return 0;
				}
			}
		}

	}

}
