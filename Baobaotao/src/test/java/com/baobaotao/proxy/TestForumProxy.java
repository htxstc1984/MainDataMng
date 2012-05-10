package com.baobaotao.proxy;

import java.lang.reflect.Proxy;

public class TestForumProxy {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// ForumServicImpl realfs = new ForumServicImpl();
		// PerformanceHandler pmh = new PerformanceHandler(realfs);
		//
		// ForumService fs = (ForumService) Proxy.newProxyInstance(realfs
		// .getClass().getClassLoader(),
		// realfs.getClass().getInterfaces(), pmh);
		//
		// fs.removeTopic(10);
		// fs.removeForum(1012);

		CglibProxy proxy = new CglibProxy();
		ForumServicImpl forumServic = (ForumServicImpl) proxy
				.getProxy(ForumServicImpl.class);

		forumServic.removeTopic(10);
		forumServic.removeForum(1012);
	}

}
