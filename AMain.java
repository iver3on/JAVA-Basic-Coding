/**
 * 
 */

/**
 * @author Iver3oN Zhang
 * @date 2016年3月21日
 * @email grepzwb@qq.com A.java Impossible is nothing
 */
public class AMain {
	static class A {
		public String show(D obj) {
			return ("A and D");
		}

		public String show(A obj) {
			return ("A and A");
		}
	}

	static class B extends A {
		public String show(B obj) {
			return ("B and B");
		}

		public String show(A obj) {
			return ("B and A");
		}
	}

	static class C extends B {
	}

	static class D extends B {
	}

	public static void main(String[] args) {
		A a1 = new A();
		A a2 = new B();
		B b = new B();
		C c = new C();
		D d = new D();
		//实例对象为A，参数为对象B，B为A的子类。执行A.class中show(A obj)
		System.out.println(a1.show(b));//A and A
		//同上
		System.out.println(a1.show(c));//A and A
		//实例对象为A，参数为对象D，执行A.class中show(D obj)
		System.out.println(a1.show(d));//A and D
		//实例对象依然为A，参数为B，本应执行A.class中show(A obj)，但是，B.class重写了show(A obj),所以执行B.class show(A obj)
		System.out.println(a2.show(b));//B and A
		//同上
		System.out.println(a2.show(c));//B and A
		//执行A.class show(D obj) B中并没有重写。
		System.out.println(a2.show(d));//A and D
		//8.实例对象为B，参数为B或者B的子类，执行show(B obj)
		System.out.println(b.show(b));//B and B
		//8.实例对象为B，参数为B或者B的子类，执行show(B obj)
		System.out.println(b.show(c));//B and B
		//实例对象为B，参数为D，因为B继承自A，也可以执行A中的show(D obj)
		System.out.println(b.show(d));//A and D
	}
}
