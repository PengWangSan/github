package com.wangp;

public class DispatcherTool {
	
	
	public static int index=0;
	
	private static NIOReactor[] reactors;
	
	
	static {
		
		int cpus= Runtime.getRuntime().availableProcessors();
		reactors=new NIOReactor[cpus];
		for(int i=0;i<cpus;i++) {
			
			
			NIOReactor nioReactor=new NIOReactor();
			nioReactor.start();
			reactors[i]=nioReactor;
		}
	}
	
	
	public static NIOReactor dispatch() {
		NIOReactor reactor=reactors[index];
		index++;
		return reactor;
	}
	

}
