package com.java.basic;

/*如果对象调用了wait方法就会使持有该对象的线程把该对象的控制权交出去，然后处于等待状态。
    如果对象调用了notify方法就会通知某个正在等待这个对象的控制权的线程可以继续运行。
    如果对象调用了notifyAll方法就会通知所有等待这个对象控制权的线程继续运行。
    基于以上几点事实，我们需要确保让线程拥有对象的控制权。
也就是说在waitThread中执行wait方法时，要保证waitThread对flag有控制权；
在notifyThread中执行notify方法时，要保证notifyThread对flag有控制权。
线程取得控制权的方法有三：
    执行对象的某个同步实例方法。
    执行对象对应类的同步静态方法。
    执行对该对象加同步锁的同步块。
我们用第三种方法来做说明：
将以上notify和wait方法包在同步块中
这时的异常是由于在针对flag对象同步块中，更改了flag对象的状态所导致的。如下：
flag=”false”;
flag.notify();
对在同步块中对flag进行了赋值操作，使得flag引用的对象改变，这时候再调用notify方法时，因为没有控制权所以抛出异常。
我们可以改进一下，将flag改成一个JavaBean，然后更改它的属性不会影响到flag的引用。
我们这里改成数组来试试，也可以达到同样的效果：

这时候再运行，不再报异常，但是线程没有结束是吧，没错，还有线程堵塞，处于wait状态。
原因很简单，我们有三个wait线程，只有一个notify线程，notify线程运行notify方法的时候，是随机通知一个正在等待的线程，所以，现在应该还有两个线程在waiting。
我们只需要将NotifyThread线程类中的flag.notify()方法改成notifyAll()就可以了。notifyAll方法会通知所有正在等待对象控制权的线程。
    */
/*
 * 如果线程调用了对象的wait（）方法，那么线程便会处于该对象的等待池中，等待池中的线程不会去竞争该对象的锁。
   当有线程调用了对象的notifyAll（）方法（唤醒所有wait线程）或notify（）方法（只随机唤醒一个wait线程），
   被唤醒的的线程便会进入该对象的锁池中，锁池中的线程会去竞争该对象锁。
   优先级高的线程竞争到对象锁的概率大，假若某线程没有竞争到该对象锁，它还会留在锁池中，
   唯有线程再次调用wait（）方法，它才会重新回到等待池中。而竞争到对象锁的线程则继续往下执行，
   直到执行完了synchronized代码块，它会释放掉该对象锁，这时锁池中的线程会继续竞争该对象锁。
 * 
 * 
 */
public class WaitNotify {
	private String flag[] = { "true" };

	class NotifyThread extends Thread {
		public NotifyThread(String name) {
			super(name);
		}
		public void run() {
			try {
				sleep(3000);// 推迟3秒钟通知
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			synchronized (flag) {
			flag[0] = "false";
			flag.notify();
			}
		}
	};

	class WaitThread extends Thread {
		public WaitThread(String name) {
			super(name);
		}

		public void run() {
			synchronized (flag) {
				while (flag[0] != "false") {
					System.out.println(getName() + " begin waiting!");
					long waitTime = System.currentTimeMillis();
					try {
						//三个等待线程都在wait 处于等待池中，等待池中的线程不会去竞争该对象flag的锁。
						flag.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					waitTime = System.currentTimeMillis() - waitTime;
					System.out.println("wait time :" + waitTime);
				}
				System.out.println(getName() + " end waiting!");
			}
		}
	}

	public static void main(String[] args) {
		System.out.println("Main Thread Run!");
		WaitNotify test = new WaitNotify();
		NotifyThread notifyThread = test.new NotifyThread("notify01");
		WaitThread waitThread01 = test.new WaitThread("waiter01");
		WaitThread waitThread02 = test.new WaitThread("waiter02");
		WaitThread waitThread03 = test.new WaitThread("waiter03");
		notifyThread.start();
		waitThread01.start();
		waitThread02.start();
		waitThread03.start();
	}
}
