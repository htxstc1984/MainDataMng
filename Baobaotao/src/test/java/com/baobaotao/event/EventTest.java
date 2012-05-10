package com.baobaotao.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EventTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext ctx = new ClassPathXmlApplicationContext("bean3.xml");
		MailSender sender = (MailSender) ctx.getBean("mailSender");
		sender.sendMail("htxstc1984@126.com");
	}

}
