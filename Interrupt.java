package com.java.basic;

//休眠线程的中断
public class Interrupt extends Object implements Runnable{
	public void run(){
		try{
			System.out.println("in run() - about to sleep for 20 seconds");
			Thread.sleep(20000);
			System.out.println("in run() - woke up");
		}catch(InterruptedException e){
			System.out.println("in run() - interrupted while sleeping");
			//处理完中断异常后，返回到run（）方法人口，
			//如果没有return，线程不会实际被中断，它会继续打印下面的信息
			//return;  
		}
		System.out.println("in run() - leaving normally");
	}


	public static void main(String[] args) {
		Interrupt si = new Interrupt();
		Thread t = new Thread(si);
		t.start();
		//主线程休眠2秒，从而确保刚才启动的线程有机会执行一段时间
		System.out.println("主线程在执行");
		/*这种模式下，main线程中断它自身。除了将中断标志（它是Thread的内部标志）设置为true外，
		 * 没有其他任何影响。线程被中断了，但main线程仍然运行，main线程继续监视实时时钟，
		 * 并进入try块，一旦调用sleep（）方法，它就会注意到待决中断的存在，并抛出InterruptException。
		 * 于是执行跳转到catch块，并打印出线程被中断的信息。最后，计算并打印出时间差。
		 */
		Thread.currentThread().interrupt();
		System.out.println("主线程状态："+Thread.currentThread().isInterrupted());
		try {
			Thread.sleep(2000); 
		}catch(InterruptedException e){
			e.printStackTrace();
		}
		System.out.println("in main() - interrupting other thread");
		//中断线程t
		t.interrupt();
		System.out.println("子线程是否中断？："+t.isInterrupted());
		System.out.println("in main() - leaving");
	}
	/*这里补充下yield和join方法的使用。
	 *join方法用线程对象调用，如果在一个线程A中调用另一个线程B的join方法，线程A将会等待线程B执行完毕后再执行。
	 *yield可以直接用Thread类调用，yield让出CPU执行权给同等级的线程，如果没有相同级别的线程在等待CPU的执行权，
	 *则该线程继续执行。
*/

}
