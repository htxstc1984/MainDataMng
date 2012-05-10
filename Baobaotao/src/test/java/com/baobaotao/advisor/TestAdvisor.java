package com.baobaotao.advisor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAdvisor {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"bean_advisor.xml");
		Waiter waiter = (Waiter) ctx.getBean("waiter");
		Seller seller = (Seller) ctx.getBean("seller");

		waiter.greetTo("htx");
		waiter.serveTo("htx");
		seller.greetTo("hqj");
	}
}
