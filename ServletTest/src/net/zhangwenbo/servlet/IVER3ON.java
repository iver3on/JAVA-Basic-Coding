/**
 * 
 */
package net.zhangwenbo.servlet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author Iver3oN Zhang
 * @date 2016年3月14日
 * @email grepzwb@qq.com
 * IVER3ON.java
 * Impossible is nothing
 */
public class IVER3ON {

	/**
	 * @param args
	 */
	public static void print(int[] array){
		for(int x:array){
			System.out.print(x+" ");
		}
		System.out.println();
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] a = {3,5,1,6,7,9,10,54,34,21};
		print(a);
		int[] b = Arrays.copyOfRange(a, 3, 8);
		Arrays.sort(a);
		print(a);
		print(b);
		System.out.println(a.toString());
		List<Integer> list = new ArrayList<>();
		list.add(4);
		list.add(1);
		list.add(7);
		Collections.sort(list);
		List<Integer> list1 = new ArrayList<>();
		list1.add(3);
		list1.add(5);
		list1.add(8);
		String s = "x";
		String s1 = "x";
		String s2 = new String("x");
		String s3 = new String("x");
		System.out.println(s==s1);
		System.out.println(s.equals(s2));
		System.out.println(s==s2);
		System.out.println(s2==s3);
		//如果两个集合中没有相同的元素 则返回true;否则返回false;
		System.err.println(Collections.disjoint(list, list1));
	}

}
