package com.study.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Bootstrap {

	public static void main(String[] args) {

		Thread t = new Thread(new DaemonT());
		t.setDaemon(true);
		t.start();

		ExecutorService es = Executors.newFixedThreadPool(3);

		for (int i = 0; i <= 10000; i++) {
			es.execute(new Thread());
		}
       System.out.println("s");
	}

}
