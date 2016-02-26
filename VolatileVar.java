package com.java.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class SyncThread3 implements Runnable{
		private volatile  boolean x  =false;
		public SyncThread3(){
			x = false;
		}
		public  void run() {		
				  for (int i = 0; i < 5; i++) {
			            try {
			            	x = true;
			               System.out.println(Thread.currentThread().getName() + ":" + x);
			               Thread.sleep(200);
			            } catch (InterruptedException e) {
			               e.printStackTrace();
			            }
		      }
		     //当一个线程访问对象的一个synchronized(this)同步代码块时，
		     //另一个线程仍然可以访问该对象中的非synchronized(this)同步代码块。 
		     //printCount();
		}
		//非synchronized代码块，未对count进行读写操作，所以可以不用synchronized
		   public void printCount() {
		      for (int i = 0; i < 5; i ++) {
		         try {
		            System.out.println(Thread.currentThread().getName() + " count:" + x);
		            Thread.sleep(100);
		         } catch (InterruptedException e) {
		            e.printStackTrace();
		         }
		      }
		   }
		public boolean getCount(){
			return x;
		}
		
}

public class VolatileVar {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List x = Collections.synchronizedList(new ArrayList<>());
		SyncThread3 thread = new SyncThread3();
		Thread thread1 = new Thread(thread, "thread1");
		Thread thread2 = new Thread(thread, "thread2");
		thread1.start();
		thread2.start();
//		SyncThread syncThread1 = new SyncThread();
//		SyncThread syncThread2 = new SyncThread();
//		Thread thread1 = new Thread(syncThread1, "SyncThread1");
//		Thread thread2 = new Thread(syncThread2, "SyncThread2");
//		thread1.start();
//		thread2.start();
	}

}
