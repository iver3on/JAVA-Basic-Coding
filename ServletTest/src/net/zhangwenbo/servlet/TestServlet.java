/**
 * 
 */
package net.zhangwenbo.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Iver3oN Zhang
 * @date 2016Äê3ÔÂ13ÈÕ
 * @email grepzwb@qq.com TestServlet.java Impossible is nothing
 */
public class TestServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public TestServlet() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	int count = 0;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().println("<HTML><BODY>");
		response.getWriter().println(this + " ==> ");
		response.getWriter().println(Thread.currentThread() + ": <br>");
		synchronized (this) {
			for (int i = 0; i < 5; i++) {
				response.getWriter().println("count = " + count + "<BR>");
				try {
					Thread.sleep(2000);
					count++;
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}	
		response.getWriter().println("</BODY></HTML>");
	}

	/**
	 * The doPost method of the servlet. <br>
	 * 
	 * This method is called when a form has its tag value method equals to
	 * post.
	 * 
	 * @param request
	 *            the request send by the client to the server
	 * @param response
	 *            the response send by the server to the client
	 * @throws ServletException
	 *             if an error occurred
	 * @throws IOException
	 *             if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 * 
	 * @throws ServletException
	 *             if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
