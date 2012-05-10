package com.baobaotao.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class PerformanceHandler implements InvocationHandler {

	private Object obj;

	public PerformanceHandler(Object obj) {
		this.obj = obj;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		// TODO Auto-generated method stub
		PerformanceMonitor.begin(method.getName());
		method.invoke(obj, args);
		PerformanceMonitor.end(method.getName());
		return obj;
	}

}
