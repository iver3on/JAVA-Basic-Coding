/**
 * 回调函数其实就是将某个特定接口的实现作为参数传入目标对象，让目标对象在适当的时候对其进行调用。
Response接口包含了两个方法：success和fail，分别需要在请求成功和失败时调用，但是具体这两个方法需要做写什么事情，
这在接口的定义中是无从知道的，因为这是根据每个发送请求的主体的具体情况而确定的。
Request是发送请求类，是执行人物的主体，在其send(Response response)方法中，会接受一个Response接口的实现，
并在请求完成后，根据请求的结果调用Response中相应的方法。
CallbackSample是测试的主体，在main函数中，产生一个Request对象，然后调用其send方法，
同时传入一个匿名类实现了Response接口。
 */
package zhangwenbo.net;

/**
 * @author Iver3oN Zhang
 * @date 2016年3月25日
 * @email grepzwb@qq.com
 * CallbackSample.java
 * Impossible is nothing
 */
public class CallbackSample {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Request request = new Request();
        request.send(new Response() {
            @Override
            public void success() {
                System.out.println("Request Success");
            }

            @Override
            public void fail() {
                System.out.println("Request Fail");
            }
        });
	}

}
