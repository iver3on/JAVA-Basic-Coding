import java.util.Arrays;

/**
 * 用数组模拟栈 注意扩容
 */

/**
 * @author IVER3ON
 * @param <T>
 * @email grepzwb@qq.com
 * 2016年5月17日
 */
public class ArrayStack<T> implements Stack<T>{
	private final int DEFAULT_SIZE=2;//数组默认的容量  
    private int capacity;//栈的容量  
    private int size;//栈的大小  
    private int top;//指向下一个要添加的元素的位置  
    private Object[] array;  
    
    public ArrayStack(){
    	this.capacity=DEFAULT_SIZE;  
        this.array=new Object[this.capacity];  
        this.size=0; 
    }
    public ArrayStack(int capacity){  
        this.capacity=capacity;  
        this.array=new Object[this.capacity];  
        this.size=0;  
    }  
	
	public void clear() {
		Arrays.fill(this.array, null);  
        this.size=0;  
        this.top=0;  
        this.capacity=DEFAULT_SIZE;  
        this.array=new Object[capacity]; 
		
	}

	public boolean isEmpty() {
		return size==0;
	}

	public T peek() {
		if(isEmpty()){  
            return null;  
        }  
        return (T) this.array[this.top-1];  
	}

	public T pop() {
		T elment=(T) this.array[top-1];  
        array[this.top-1]=null;  
        this.top=this.top-1;  
        this.size--;  
        return elment;  
	}

	public void push(T elment) {
		 if(this.size<this.capacity){  
	            this.array[top]=elment;  
	            this.size++;  
	            this.top++;  
	        }  
	        else {   //若栈的容量不够则扩充栈的容量  
	            addStackCapacity();  
	            push(elment);  
	        }         
	}

	private void addStackCapacity() {
		this.capacity=this.capacity+DEFAULT_SIZE;//默认增加的幅度为2  
        Object[] newArray=new Object[this.capacity];  
        System.arraycopy(this.array, 0, newArray, 0,this.array.length);//把旧数组的值复制到新的数组中  
        Arrays.fill(array, null);//把原来的数组的值都变为空  
        this.array=newArray; 
	}
	public int size() {
		 return this.size;  
	}
	
	
	public static void main(String[] args) {
		 ArrayStack<Integer> testStack=new ArrayStack<Integer>();  
	        testStack.push(3);  
	        testStack.push(5);  
	        testStack.push(2);  
	        testStack.push(1);  
	        testStack.push(6);  
	        System.out.println("栈大小:"+testStack.size());  
	        System.out.println("栈的容量:"+testStack.capacity);  
	        System.out.println("栈顶元素:"+testStack.peek());  
	        while(!testStack.isEmpty()){  
	            System.out.println(testStack.pop());  
	        }  
	        System.out.println("栈的大小:"+testStack.size());  
	        System.out.println("栈顶元素:"+testStack.peek());  
	        System.out.println("栈的容量:"+testStack.capacity);  
	        System.out.println("*****************"+"\n");  
	        testStack.push(1);  
	        testStack.push(6);  
	        testStack.push(2);  
	        System.out.println("栈的大小:"+testStack.size());  
	        testStack.clear();  
	        System.out.println("清空栈后栈的大小:"+testStack.size());  
	        System.out.println("清空栈后栈的容量:"+testStack.capacity);  
	}

}
