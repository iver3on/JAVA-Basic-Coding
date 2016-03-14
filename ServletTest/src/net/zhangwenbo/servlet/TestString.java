/**
 * 
 */
package net.zhangwenbo.servlet;

/**
 * @author Iver3oN Zhang
 * @date 2016年3月14日
 * @email grepzwb@qq.com
 * TestString.java
 * Impossible is nothing
 */
public class TestString {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/*输出结果为：true。对于被final修饰的变量，
		 * 会在class文件常量池中保存一个副本，也就是说不会通过连接而进行访问，
		 * 对final变量的访问在编译期间都会直接被替代为真实的值。那么String c = b + 2;在编译期间就会被优化成：
		 * String c = “hello” + 2
		 */
		String a = "hello2";
		final String b = "hello";       
		String c = b + 2;       
		System.out.println((a == c));
	}

}
