/**
 * 
 */
package net.zwb;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author IVER3ON
 * @email grepzwb@qq.com
 * 2016年5月16日
 */
public class ConcurrentHashMapTest {
	static ConcurrentHashMap<Integer, Integer> map = new ConcurrentHashMap<>();
	/**
	 * @param args
	 * 
	 * ConcurrentHashMap中默认是把segments初始化为长度为16的数组。
根据ConcurrentHashMap.segmentFor的算法，3、4对应的Segment都是segments[1]，7对应的Segment是segments[12]。
（1）Thread1和Thread2先后进入Segment.put方法时，Thread1会首先获取到锁，可以进入，而Thread2则会阻塞在锁上：
（2）切换到Thread3，也走到Segment.put方法，因为7所存储的Segment和3、4不同，因此，不会阻塞在lock()：
以上就是ConcurrentHashMap的工作机制，通过把整个Map分为N个Segment（类似HashTable），可以提供相同的线程安全，但是效率提升N倍，默认提升16倍。
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		  new Thread("Thread1"){  
	            @Override  
	            public void run() {  
	                map.put(3, 33);  
	            }  
	        };  
	          
	        new Thread("Thread2"){  
	            @Override  
	            public void run() {  
	                map.put(4, 44);  
	            }  
	        };  
	          
	        new Thread("Thread3"){  
	            @Override  
	            public void run() {  
	                map.put(7, 77);  
	            }  
	        };  
	        System.out.println(map);  
	}

}
