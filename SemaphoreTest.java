package com.java.basic;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {
// 可以看出，Semaphore允许并发访问的任务数一直为5，当然，这里还很容易看出一点，
	//就是Semaphore仅仅是对资源的并发访问的任务数进行监控，而不会保证线程安全，
	//因此，在访问的时候，要自己控制线程的安全访问。
	public static void main(String[] args) {
	ExecutorService executorService = Executors.newCachedThreadPool();
	final Semaphore semp = new Semaphore(5);
	  for (int index = 0; index < 10; index++){  
	        final int num = index;  
	        Runnable run = new Runnable() {  
	            public void run() {  
	                try {  
	                    //获取许可  
	                    semp.acquire();  
	                    System.out.println("线程" +   
	                        Thread.currentThread().getName() + "获得许可："  + num);  
	                    //模拟耗时的任务  
	                    for (int i = 0; i < 999999; i++) ;  
	                    //释放许可  
	                    //semp.release();  
	                   // System.out.println("线程" +   
	                     //   Thread.currentThread().getName() + "释放许可："  + num);  
	                    System.out.println("当前允许进入的任务个数：" +  
	                        semp.availablePermits());  
	                }catch(InterruptedException e){  
	                    e.printStackTrace();  
	                }  
	            }  
	        };  
	        executorService.execute(run);
	}
	}
}
