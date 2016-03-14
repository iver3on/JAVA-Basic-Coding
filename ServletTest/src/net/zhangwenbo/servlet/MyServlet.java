/**
 * 
 */
package net.zhangwenbo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Iver3oN Zhang
 * @date 2016年3月10日
 * @email grepzwb@qq.com
 * MyServlet.java
 * Impossible is nothing
 */
public class MyServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public MyServlet() {
		super();
		System.out.println("构造函数MyServlet");
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
		System.out.println("销毁destroy");
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//response.sendRedirect("/WEB-INF/jsp/hello.jsp");	
		System.out.println("getContextPath:"+request.getContextPath());
		System.out.println("getServletContext:"+request.getServletContext());
		System.out.println("getServletPath:"+request.getServletPath());
		System.out.println("getRequestURI:"+request.getRequestURI());
		System.out.println("getRequestURL:"+request.getRequestURL());
		RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/hello.jsp");
		rd.forward(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	

	@Override
	protected void service(HttpServletRequest arg0, HttpServletResponse arg1)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(arg0, arg1);
	}

	@Override
	public void service(ServletRequest arg0, ServletResponse arg1)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(arg0, arg1);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
		System.out.println("初始化init");
	}

}
