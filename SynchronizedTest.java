package com.java.basic;

class Account{
	String name;
	int money;
	public Account(String name, int money) {
		this.name = name;
		this.money = money;
	}
	//模拟存款的方法
	public void deposit(int dep){
		int tmp = money;//中间变量 模拟数据库操作
		tmp +=dep;
		try {
            Thread.sleep(100);//模拟其它处理所需要的时间，比如刷新数据库等
        } catch (InterruptedException e) {
            // ignore
        } 
        money = tmp;//存入数据库
}
	
	//模拟取款的方法
	public  void withdraw(int wid) {
        int tmp = money;
        tmp -= wid;

        try {
            Thread.sleep(100);//模拟其它处理所需要的时间，比如刷新数据库等
        } catch (InterruptedException e) {
            // ignore
        }       
        money = tmp;
    }

    public int getBalance() {
        return money;
    }
}

/**
 * 账户操作类
 */
class AccountOperator implements Runnable{
   private Account account;
   public AccountOperator(Account account) {
      this.account = account;
   }

   public void run() {
     // synchronized (account) {
         account.deposit(100);
         account.withdraw(100);
         //System.out.println(Thread.currentThread().getName() + ":" + account.getBalance());
    //  }
   }
}

//同步加锁的是对象，而不是代码。
public class SynchronizedTest {
	//模拟1000个人取款存款
	private static int NUM_OF_THREAD = 1000;
	static Thread[] threads = new Thread[NUM_OF_THREAD];
	public static void main(String[] args) {
		 final Account acc = new Account("John", 1000);
		 AccountOperator accountOperator = new AccountOperator(acc);
	        for (int i = 0; i< NUM_OF_THREAD; i++) {
	            threads[i] = new Thread(accountOperator,"Thread"+i);
	            threads[i].start();
	        }

	        for (int i=0; i<NUM_OF_THREAD; i++){
	            try {
	                threads[i].join(); //等待所有线程运行结束
	            } catch (InterruptedException e) {
	                // ignore
	            }
	        }
	        System.out.println("Finally, John's balance is:" + acc.getBalance());
	}
	/*运行SyncTest产生的输出是1和3交叉的。如果printVal是断面，你看到的输出只能是1或者只能是3而不能是两者同时出现。
	 * 程序运行的结果证明两个线程都在并发的执行printVal方法，
	 * 即该方法是同步的并且由于是一个无限循环而没有终止。
	 */

	class Foo extends Thread {
		private int val;

		public Foo(int v) {
			val = v;
		}

	/*	public void printVal(int v) {
			synchronized (Foo.class) {
				int i = 0;
				while (i < 10) {
					System.out.println(v);
					i++;
				}
			}
		}*/

		 public void printVal(int v){ 
			 int i=0; 
			 while(i<5)
			 { System.out.println(v);
			 	i++; 
			 	} 
			 }
		public void run() {
			printVal(val);
		}
	}

}
