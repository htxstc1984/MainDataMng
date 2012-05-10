package com.baobaotao.aspectj.aspectj;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestAspectJ {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ctx = new ClassPathXmlApplicationContext(
				"bean_aspectj.xml");
		WaiterDelegate waiterDelegate = (WaiterDelegate) ctx
				.getBean("waiterDelegate");

		waiterDelegate.service();

	}

}
