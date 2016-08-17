/**
 * 不知道小伙伴们记不记得Object类有一个finalize()方法，所有类都继承了Object类，因此也默认实现了这个方法。
        这个方法的用途就是：在该对象被回收之前，该对象的finalize()方法会被调用。这里的回收之前指的就是被标记之后，问题就出在这里，
        有没有一种情况就是原本一个对象开始不再上一章所讲的“关系网”（引用链）中，但是当开发者重写了finalize()后，并且将该对象重新加入到了“关系网”中，
        也就是说该对象对我们还有用，不应该被回收，但是已经被标记啦，怎么办呢？
        针对这个问题，虚拟机的做法是进行两次标记，即第一次标记不在“关系网”中的对象。第二次的话就要先判断该对象有没有实现finalize()方法了，
        如果没有实现就直接判断该对象可回收；如果实现了就会先放在一个队列中，并由虚拟机建立的一个低优先级的线程去执行它，随后就会进行第二次的小规模标记
        ，在这次被标记的对象就会真正的被回收了。我们来看下面的代码：
 */

/**
 * @author Iver3on
 * @date 2016年8月17日
 */
public class TestFinalize {

	private static TestFinalize test = null;// 一个类静态变量

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		test = new TestFinalize();
		testHelp();
		testHelp();
	}

	/**
	 * @throws InterruptedException
	 * 
	 */
	private static void testHelp() throws InterruptedException {
		// TODO Auto-generated method stub
		test = null;// 将其从关系网中剔除
		// 通知进行垃圾回收 但不一定会垃圾回收 只是一个通知而已
		// 因此在开发中不要过多的依赖这个方法 这是做一个测试而已
		System.gc();

		// 等待一秒钟
		Thread.sleep(1000);
		if (test == null) {
			System.out.println("我挂了");
		} else {
			System.out.println("我还活着");
		}
	}

	@Override
	protected void finalize() throws Throwable {
		// TODO Auto-generated method stub
		super.finalize();
		test = this;// 将其重新加入关系网中
		System.out.println("我被调用啦");
	}

	
	/*
	 * 大家觉得他会输出什么？最后的结果是：
        我被调用啦
        我还活着
        我挂啦
有木有觉得很诧异，明明调用了两次同样的方法，但输出怎么不同呢？而且明明调用了两次gc()方法（这里确认是执行了gc）,那怎么只进入了一次finalize()方法？
        嘿嘿，其实面对同一个对象，他的finalize()方法只会被调用一次，因此第一次调用的时候会进行finalize()方法，并且成功的将该对象加入了“关系网”中，
        但当第二次回收的时候并不会进入，所以第二次不能将对象加入“关系网”中，导致被回收了。
        图中有一行让程序睡眠一秒钟的代码，为的就是确保让低优先级的执行finalize()方法线程执行完成。那如果我们把他注释了会怎样呢？输出结果是：
        我挂啦
        我被调用啦
        我挂啦
        很奇怪吧，不过如果执行很多次的话，也会出现最开始那样的结果，但多数会是这个结果。因为我们已经说了，执行finalize()的是一个低优先级的线程，
        既然是一个新的线程，虽然优先级低了点，但也是和垃圾收集器并发执行的，所以垃圾收集器没必要等这个低优先级的线程执行完才继续执行。
        也就是说，finalize()方法不一定会在对象第一次标记后执行。用一句清晰易懂的话来说就是：虚拟机确实有调用方法的动作，但是不会确保在什么时候执行完成。
        因此也就出现了上面输出的结果，对象被回收之后，那个低优先级的线程才执行完。
        讲到这里，堆相关的知识也就大概讲完了，讲了好几章。我们时常Mark,是时候停下来整理这些Mark了！
	 * 
	 */
}
