package net.zwb;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

//将读的文件中的字符大小写转换   装饰器模式
class MyOwnInputStream extends FilterInputStream {

	protected MyOwnInputStream(InputStream in) {
		super(in);
		// TODO Auto-generated constructor stub
	}

	@Override
	public int read() throws IOException {
		int c = 0;
		if ((c = super.read()) != -1) {
			if (Character.isLowerCase((char) c)) {
				return Character.toUpperCase((char) c);
			} else if (Character.isUpperCase((char) c)) {
				return Character.toLowerCase((char) c);
			} else
				return c;
		} else {
			return -1;
		}
	}

}

public class MyInputStream {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		int c;
		InputStream is = new MyOwnInputStream(new BufferedInputStream(
				new FileInputStream("test.txt")));
		while ((c = is.read()) >= 0) {
			System.out.print((char) c);
		}
		is.close();
	}

}
