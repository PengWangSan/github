package com.study.thread;

public class ThreadT implements Runnable {

	static long i = 0;

	public void run() {

		for (int j = 0; j <= 99999999; j++) {
			i++;
		}
	}

	

	public static void main(String[] args) throws InterruptedException {

		for (int i = 1; i <= 2; i++) {
			Thread t1 = new Thread(new ThreadT());
			t1.start();
			t1.join();
		}

		System.out.print(ThreadT.i);

	}

}
