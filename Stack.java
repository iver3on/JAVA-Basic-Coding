/**
 *阿里巴巴面试题 用数组实现栈
 */

/**
 * @author IVER3ON
 * @email grepzwb@qq.com
 * 2016年5月17日
 */
public interface Stack<T> {
	 public void clear();  
	    public boolean isEmpty();  
	    public T peek();  
	    public T pop();  
	    public void push(T elment);  
	    public int size();  
}
