package com.baobaotao.proxy;

public class MethodPerformance {
	private long begin;
	private long end;
	private String serviceMethod;

	public MethodPerformance(String serviceMethod) {
		this.serviceMethod = serviceMethod;
		begin = System.currentTimeMillis();
	}

	public void printPerformance() {
		end = System.currentTimeMillis();
		System.out.println(serviceMethod + "花费:" + (begin - end));
	}
}
