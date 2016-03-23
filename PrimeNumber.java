import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * CVTE面试题 prime number
 */
/**
 * @author Iver3oN Zhang
 * @date 2016年3月22日
 * @email grepzwb@qq.com PrimeNumber.java Impossible is nothing
 */
public class PrimeNumber {

	/**
	 * 素数就是质数,即除了1和它本身以外任何数都不能整除他的数 素数可以这样算出来：将你知道的素数全部乘起来再加一.
	 * 第一，对于一个自然数N，只要能被一个非1非自身的数整除，它就肯定不是素数，所以不 必再用其他的数去除。
	 * 第二，对于N来说，只需用小于N的素数去除就可以了。例如，如果N能被15整除，实际
	 * 上就能被3和5整除，如果N不能被3和5整除，那么N也决不会被15整除。
	 * 第三，对于N来说，不必用从2到N一1的所有素数去除，只需用小于等于√N(根号N)的所有素数去除就可以了。
	 * 检查一个正整数N是否为素数，最简单的方法就是试除法，将该数N用小于等于根号N的所有素数去试除，若均无法整除，N则为素数，参见素数判定法则。
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> list = new ArrayList<>();
		list.add(2);
		list.add(3);
		list.add(5);
		for (int i = 7; i <= 1000; i++) {
			// 用于标识这个数一直没有被整除
			boolean tag = false;
			for (int j = 0; j < list.size(); j++) {
				// 只需要判断小于等于根号i的数能否整除i即可
				if (list.get(j) <= Math.sqrt(i) && i % list.get(j) == 0) {
					tag = true;
					break;
				} else {
					continue;
				}
			}
			if (!tag) {
				list.add(i);
			}
		}

		for (int x : list) {
			System.out.print(x + " ");
		}

	}
	/*
	 * 用6N±1法求素数。
任何一个自然数，总可以表示成为如下的形式之一：
6N，6N+1，6N+2，6N+3，6N+4，6N+5 (N=0，1，2，…)
显然，当N≥1时，6N，6N+2，6N+3，6N+4都不是素数，只有形如6N+1和6N+5的自然数有可能是素数。
所以，除了2和3之外，所有的素数都可以表示成6N±1的形式(N为自然数)。
根据上述分析，我们可以构造另一面筛子，只对形如6 N±1的自然数进行筛选，这样就可以大大减少筛选的次数，
从而进一步提高程序的运行效率和速度。
在程序上，我们可以用一个二重循环实现这一点，外循环i按3的倍数递增，内循环j为0－1的循环，则2(i+j)-1恰好就是形如6N±1的自然数
	 */

	@Test
	public static void getPrimNumber() {
		List<Integer> list = new ArrayList<>();
		list.add(2);
		list.add(3);
		for (int i = 0; i <= 1000; i = i + 3) {
			for (int j = 0; j < 1; j++) {
				// 用于标识这个数一直没有被整除
				int num = 2 * (i + j) - 1;
				boolean tag = false;
				for (int k = 0; k < list.size(); k++) {
					// 只需要判断小于等于根号i的数能否整除i即可
					if (list.get(j) <= Math.sqrt(num) && num % list.get(j) == 0) {
						tag = true;
						break;
					} else {
						continue;
					}
				}
				if (!tag) {
					list.add(num);
				}
			}

		}

		for (int x : list) {
			System.out.print(x + " ");
		}
	}
}
