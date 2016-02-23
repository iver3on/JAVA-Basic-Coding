package com.java.basic;

class SyncThread1 implements Runnable {
	private static int count;

	public SyncThread1() {
		count = 0;
	}

	/*
	 * syncThread1和syncThread2是SyncThread的两个对象，但在thread1和thread2并发执行时却保持了线程同步。
	 * 这是因为run中调用了静态方法method，而静态方法是属于类的， 所以syncThread1和syncThread2相当于用了同一把锁
	 */
	public synchronized static void method() {
		for (int i = 0; i < 5; i++) {
			try {
				System.out.println(Thread.currentThread().getName() + ":"
						+ (count++));
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		method();
	}

}

class SyncThread2 implements Runnable {
	private  int count;

	public SyncThread2() {
		count = 0;
	}

	public void method() {
		//synchronized作用于一个类T时，是给这个类T加锁，T的所有对象用的是同一把锁
		synchronized (SyncThread2.class) {
			for (int i = 0; i < 5; i++) {
				try {
					System.out.println(Thread.currentThread().getName() + ":"
							+ (count++));
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		method();
	}

}

public class SyncThreadStatic {

	//运行结果满足加锁结果 两个线程按序操作count
	public static void main(String[] args) {
		SyncThread2 sync = new SyncThread2();
		SyncThread2 sync1 = new SyncThread2();
		Thread t1 = new Thread(sync, "t1");
		Thread t2 = new Thread(sync1, "t2");
		t1.start();
		t2.start();
	}

}
