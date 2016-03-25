/**
 * 
 */
package zhangwenbo.net;

/**
 * @author Iver3oN Zhang
 * @date 2016Äê3ÔÂ25ÈÕ
 * @email grepzwb@qq.com
 * Request.java
 * Impossible is nothing
 */
public class Request {
	public void send(Response response) {
        System.out.println("Send Request");
        response.fail();
    }

}
