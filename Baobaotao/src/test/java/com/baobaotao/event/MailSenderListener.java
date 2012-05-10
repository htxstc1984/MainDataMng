package com.baobaotao.event;

import org.springframework.context.ApplicationListener;

public class MailSenderListener implements ApplicationListener<MailSendEvent> {

	@Override
	public void onApplicationEvent(MailSendEvent event) {
		// TODO Auto-generated method stub

		// MailSendEvent mse = (MailSendEvent)event;
		System.out.println("listener向"+event.getTo()+"发送一封邮件");
		
		
	}

}
