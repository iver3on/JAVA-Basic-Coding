/**
 * 
 */
package net.zwb;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.LinkedList;
import java.util.logging.Logger;

import javax.sql.DataSource;

/**
 * @author IVER3ON
 * @email grepzwb@qq.com 2016年5月16日
 */
public class TestConnectPool implements DataSource {

	// 链表 --- 实现栈结构
	private LinkedList<Connection> dataSources = new LinkedList<Connection>();

	public TestConnectPool() {
		// 一次性创建10个连接
		for (int i = 0; i < 10; i++) {
			try {
				// 1、装载sqlserver驱动对象
				DriverManager.registerDriver(new SQLServerDriver());
				// 2、通过JDBC建立数据库连接
				Connection con = DriverManager.getConnection(
						"jdbc:sqlserver://127.0.0.1:1433;DatabaseName=user",
						"root", "root");
				// 3、将连接加入连接池中
				dataSources.add(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.sql.CommonDataSource#getLogWriter()
	 */
	@Override
	public PrintWriter getLogWriter() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.sql.CommonDataSource#setLogWriter(java.io.PrintWriter)
	 */
	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.sql.CommonDataSource#setLoginTimeout(int)
	 */
	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.sql.CommonDataSource#getLoginTimeout()
	 */
	@Override
	public int getLoginTimeout() throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.sql.CommonDataSource#getParentLogger()
	 */
	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Wrapper#unwrap(java.lang.Class)
	 */
	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.sql.Wrapper#isWrapperFor(java.lang.Class)
	 */
	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.sql.DataSource#getConnection()
	 */
	@Override
	public Connection getConnection() throws SQLException {
		// 取出连接池中一个连接
		final Connection conn = dataSources.removeFirst(); // 删除第一个连接返回
		return conn;
	}

	// 将连接放回连接池
	public void releaseConnection(Connection conn) {
		dataSources.add(conn);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.sql.DataSource#getConnection(java.lang.String,
	 * java.lang.String)
	 */
	@Override
	public Connection getConnection(String username, String password)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//1、使用连接池建立数据库连接  
		TestConnectPool dataSource = new TestConnectPool();  
	       Connection conn =dataSource.getConnection();          
	       //2、创建状态  
	       Statement state =con.createStatement();             
	       //3、查询数据库并返回结果  
	       ResultSet result =state.executeQuery("select * from users");             
	       //4、输出查询结果  
	       while(result.next()){  
	              System.out.println(result.getString("email"));  
	       }              
	       //5、断开数据库连接  
	       result.close();  
	       state.close();  
	       //6、归还数据库连接给连接池  
	       dataSource.releaseConnection(conn);  

	}

}
