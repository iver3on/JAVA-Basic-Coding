import java.util.Arrays;

/**
 * 二分查找
 */

/**
 * @author Iver3oN Zhang
 * @date 2016年3月15日
 * @email grepzwb@qq.com
 * MM.java
 * Impossible is nothing
 */
public class MM {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[] x = {3,5,6,7,8,37,65};
		int i  =BinarySeek(x, 5);
		System.out.println(x[i]);
		System.out.println(binarySearch(x,5,0,x.length-1));  
	}

	public static int BinarySeek(int[] a,int val){
		Arrays.sort(a);
		int begin = 0;
		int end = a.length - 1;
		while(begin<end){
			int mid = (begin+end)/2;
			if(val==a[mid]){
				return mid;
			}else if(val<a[mid]){
				end = mid-1;
			}else{
				begin = mid+1;
			}
		}
		return -1;
	}
	/**   
     *二分查找特定整数在整型数组中的位置(递归)   
     *@paramdataset   
     *@paramdata   
     *@parambeginIndex   
     *@paramendIndex   
     *@returnindex   
     */  
	public static int binarySearch(int[] dataset,int data,int beginIndex,int endIndex){
		Arrays.sort(dataset);
	       int midIndex = (beginIndex+endIndex)/2;    
	       if(data <dataset[beginIndex]||data>dataset[endIndex]||beginIndex>endIndex){  
	           return -1;    
	       }  
	       if(data <dataset[midIndex]){    
	           return binarySearch(dataset,data,beginIndex,midIndex-1);    
	       }else if(data>dataset[midIndex]){    
	           return binarySearch(dataset,data,midIndex+1,endIndex);    
	       }else {    
	           return midIndex;    
	       }    
	   }   
}
