package com.baobaotao.proxy;

public class ForumServicImpl {// implements ForumService

	public void removeTopic(int topicId) {
		// TODO Auto-generated method stub
		// PerformanceMonitor
		// .begin("com.baobaotao.proxy.ForumServiceImpl.removeTopic");
		System.out.println("模拟删除话题。。。。");
		try {
			Thread.currentThread().sleep(20);
		} catch (Exception e) {
			// TODO: handle exception
		}
		// PerformanceMonitor
		// .end("com.baobaotao.proxy.ForumServiceImpl.removeTopic");
	}

	public void removeForum(int forumId) {
		// TODO Auto-generated method stub
		// PerformanceMonitor
		// .begin("com.baobaotao.proxy.ForumServiceImpl.removeForum");
		System.out.println("模拟删除板块。。。。");
		try {
			Thread.currentThread().sleep(20);
		} catch (Exception e) {
			// TODO: handle exception
		}
		// PerformanceMonitor
		// .end("com.baobaotao.proxy.ForumServiceImpl.removeForum");
	}

}
