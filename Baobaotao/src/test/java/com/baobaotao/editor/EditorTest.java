package com.baobaotao.editor;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class EditorTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
				"bean2.xml");

		Boss boss = (Boss) ctx.getBean("boss");

		boss.getCar().introduce();
	}

}
