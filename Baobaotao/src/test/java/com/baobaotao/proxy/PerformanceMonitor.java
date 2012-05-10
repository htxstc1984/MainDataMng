package com.baobaotao.proxy;

public class PerformanceMonitor {
	public static ThreadLocal<MethodPerformance> performanceRecord = new ThreadLocal<MethodPerformance>();

	public static void begin(String serviceMethod) {
		System.out.println("begin monitor...");
		MethodPerformance mp = new MethodPerformance(serviceMethod);
		performanceRecord.set(mp);

	}

	public static void end(String serviceMethod) {
		System.out.println("begin monitor...");
		MethodPerformance mp = performanceRecord.get();
		mp.printPerformance();
	}
}
