package com.baobaotao.event;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class MailSender implements ApplicationContextAware {

	private ApplicationContext ctx;

	public MailSender() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void setApplicationContext(ApplicationContext ctx)
			throws BeansException {
		// TODO Auto-generated method stub
		this.ctx = ctx;
	}

	public void sendMail(String to) {
		System.out.println("模拟发送邮件");
		MailSendEvent mse = new MailSendEvent(this.ctx, to);
		ctx.publishEvent(mse);
	}

}
