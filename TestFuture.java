import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*创建线程的2种方式，一种是直接继承Thread，另外一种就是实现Runnable接口。
这2种方式都有一个缺陷就是：在执行完任务之后无法获取执行结果。
 * 如果需要获取执行结果，就必须通过共享变量或者使用线程通信的方式来达到效果，这样使用起来就比较麻烦。

　　而自从Java 1.5开始，就提供了Callable和Future，通过它们可以在任务执行完毕之后得到任务执行结果。

　　今天我们就来讨论一下Callable、Future和FutureTask三个类的使用方法。以下是本文的目录大纲：

　　一.Callable与Runnable

　　二.Future

　　三.FutureTask
 */
//使用Callable+Future获取执行结果
public class TestFuture {
	public static void main(String[] args) {
		ExecutorService executor = Executors.newCachedThreadPool();
		Task task = new Task();
		Future<Integer> result = executor.submit(task);
		executor.shutdown();

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		System.out.println("主线程在执行任务");

		try {
			System.out.println("task运行结果" + result.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}

		System.out.println("所有任务执行完毕");
	}
}

class Task implements Callable<Integer> {
	@Override
	public Integer call() throws Exception {
		System.out.println("子线程在进行计算");
		Thread.sleep(3000);
		int sum = 0;
		for (int i = 0; i < 100; i++)
			sum += i;
		return sum;
	}
}
