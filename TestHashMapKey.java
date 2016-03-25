import java.util.HashMap;
import java.util.Map;

/**在HashMap中，查找key的比较顺序为：
计算对象的Hash Code，看在表中是否存在。
检查对应Hash Code位置中的对象和当前对象是否相等。
 */

/**
 * @author Iver3oN Zhang
 * @date 2016年3月25日
 * @email grepzwb@qq.com
 * T.java
 * Impossible is nothing
 */
public class TestHashMapKey{

	static class Person{

		String id;
		public Person(String id){
			this.id = id;
		}
		@Override
		public int hashCode() {
			//所以相同内容的字符串具有相同的Hash Code。
			return id != null ? id.hashCode() : 0;
		}

		//而在Object中，Hash Code的计算方法是根据对象的地址进行计算的，
		//那两个Person("003")的对象地址是不同的，所以它们的Hash Code也不同，
		//自然HashMap也不会把它们当成是同一个key了
		/*
		 * 为什么在已知hashCode()相等的情况下，还需要用equals()进行比较呢？
		 * 就是因为避免出现上述例子中的出现的情况，因为根据对Person类的hashCode()方法的重载实现，
		 * Person类会直接用id这个String类型成员的Hash Code值作为自己的Hash Code值，但是很显然的，
		 * 一个Person("003")和一个String("003")是不相等的，所以在hashCode()相等的情况下，
		 * 还需要用equals()进行比较。 
		 * */
		@Override
		public boolean equals(Object o) {
			 if (this == o) return true;
		        if (o == null || getClass() != o.getClass()) return false;
		        Person person = (Person) o;
		        if (id != null ? !id.equals(person.id) : person.id != null) return false;
		        return true;
		}	
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<Person,Integer> map = new HashMap<>();
		map.put(new Person("1"),11);
		map.put(new Person("2"),34);
		map.put(new Person("3"), 21);
		map.put(new Person("3"), 31);
		System.out.println(map.toString());
		System.out.println(map.get(new Person("1")));
		System.out.println(map.get(new Person("2")));
		System.out.println(map.get(new Person("3")));
	}

}
