package com.baobaotao.reflect;

import java.lang.reflect.Proxy;

public class ProxyTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RealRequest request = new RealRequest();
		RequestProxy proxy = new RequestProxy(request);

		Class cls = request.getClass();

		Request req = (Request) Proxy.newProxyInstance(cls.getClassLoader(),
				cls.getInterfaces(), proxy);

		req.say();
	}

}
