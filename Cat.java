/**
 * 接口是公开的，里面不能有私有的方法或变量，是用于让别人使用的，而抽象类是可以有私有方法或私有变量的，

另外，实现接口的一定要实现接口里定义的所有方法，而实现抽象类可以有选择地重写需要用到的方法，
一般的应用里，最顶级的是接口，然后是抽象类实现接口，最后才到具体类实现。

还有，接口可以实现多重继承，而一个类只能继承一个超类，但可以通过继承多个接口实现多重继承，
接口还有标识（里面没有任何方法，如Remote接口）和数据共享（里面的变量全是常量）的作用.
 */
//抽象类不用全部实现接口中的所有方法，其余的方法实现可以交给该抽象类的子类去实现即可。

/**
 * @author Iver3on
 * @date 2016年8月27日
 */
abstract class Animal{
    abstract void say();
    abstract void hi();
}	
public class Cat extends Animal{
    public Cat(){
        System.out.printf("I am a cat");
    }
    public static void main(String[] args) {
        Cat cat=new Cat();
    }
	/* (non-Javadoc)
	 * @see Animal#say()
	 */
	@Override
	void say() {
		// TODO Auto-generated method stub
		
	}
	/* (non-Javadoc)
	 * @see Animal#hi()
	 */
	/* (non-Javadoc)
	 * @see Animal#hi()
	 */
	@Override
	void hi() {
		// TODO Auto-generated method stub
		
	}

}
