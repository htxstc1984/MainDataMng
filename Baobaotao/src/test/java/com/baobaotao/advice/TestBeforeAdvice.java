package com.baobaotao.advice;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestBeforeAdvice {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// NaiveWaiter target = new NaiveWaiter();
		// ProxyFactory f = new ProxyFactory(target);
		//
		// f.addAdvice(new GreetingBeforeAdvice());
		// // f.setOptimize(true);
		// Waiter waiter = (Waiter) f.getProxy();
		//
		// waiter.greetTo("htx");
		

		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"bean_advice.xml");

		Waiter waiter = (Waiter) ctx.getBean("waiter");
		waiter.greetTo("htx");
		waiter.serveTo("htx");
	}

}
