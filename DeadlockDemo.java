package com.java.basic;

/*当线程需要同时持有多个锁时，有可能产生死锁。考虑如下情形
 * 线程A当前持有互斥所锁lock1，线程B当前持有互斥锁lock2。
 * 接下来，当线程A仍然持有lock1时，它试图获取lock2，因为线程B正持有lock2，
 * 因此线程A会阻塞等待线程B对lock2的释放。如果此时线程B在持有lock2的时候，也在试图获取lock1，
 * 因为线程A正持有lock1，因此线程B会阻塞等待A对lock1的释放。二者都在等待对方所持有锁的释放，
 * 而二者却又都没释放自己所持有的锁，这时二者便会一直阻塞下去。这种情形称为死锁。
 */
public class DeadlockDemo {

	//线程ID
	private int num;
	private String objID;

	public DeadlockDemo(String id) {
		objID = id;
	}

	public synchronized void checkOther(DeadlockDemo other) {
		print("entering checkOther()");
		try { Thread.sleep(2000); } 
		catch ( InterruptedException x ) { }
		print("in checkOther() - about to " + "invoke 'other.action()'");

		//调用other对象的action方法，由于该方法是同步方法，因此会试图获取other对象的对象锁
		other.action();
		print("leaving checkOther()");
	}

	//同步方法 会阻塞另外的一个线程 执行  互相等待产生死锁
	public synchronized void action() {
		print("entering action()");
		num++;
		try { Thread.sleep(500); } 
		catch ( InterruptedException x ) { }
		print("leaving action()");
	}

	public void print(String msg) {
		threadPrint("objID=" + objID + " - " + msg);
	}

	public static void threadPrint(String msg) {
		String threadName = Thread.currentThread().getName();
		System.out.println(threadName + ": " + msg);
	}

	public static void main(String[] args) {
		final DeadlockDemo obj1 = new DeadlockDemo("obj1");
		final DeadlockDemo obj2 = new DeadlockDemo("obj2");

		Runnable runA = new Runnable() {
				public void run() {
					obj1.checkOther(obj2);
				}
			};

		Thread threadA = new Thread(runA, "threadA");
		threadA.start();

		try { Thread.sleep(200); } 
		catch ( InterruptedException x ) { }

		Runnable runB = new Runnable() {
				public void run() {
					obj2.checkOther(obj1);
				}
			};

		Thread threadB = new Thread(runB, "threadB");
		threadB.start();

		try { Thread.sleep(5000); } 
		catch ( InterruptedException x ) { }

		threadPrint("main thread finished sleeping");

		threadPrint("main thread about to interrupt() threadA");
		threadA.interrupt();

		try { Thread.sleep(1000); } 
		catch ( InterruptedException x ) { }

		threadPrint("main thread about to interrupt() threadB");
		threadB.interrupt();

		try { Thread.sleep(1000); } 
		catch ( InterruptedException x ) { }

		threadPrint("did that break the deadlock?");
	}

}
