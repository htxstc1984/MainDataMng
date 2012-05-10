package com.baobaotao.advice;

public class NaiveWaiter implements Waiter {

	public void greetTo(String name) {
		// TODO Auto-generated method stub
		System.out.println("greet to..." + name);
	}

	public void serveTo(String name) {
		// TODO Auto-generated method stub
		System.out.println("serve to..." + name);
	}

}
