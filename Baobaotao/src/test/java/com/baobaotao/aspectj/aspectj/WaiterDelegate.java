package com.baobaotao.aspectj.aspectj;

public class WaiterDelegate {
	private Waiter waiter;

	public void service() {
		waiter.greetTo("htx");
		waiter.serveTo("htx");
	}

	public Waiter getWaiter() {
		return waiter;
	}

	public void setWaiter(Waiter waiter) {
		this.waiter = waiter;
	}

}
