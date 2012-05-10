package com.baobaotao.proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibProxy implements MethodInterceptor {

	private Enhancer enhancer = new Enhancer();

	public Object getProxy(Class clazz) {
		enhancer.setSuperclass(clazz);
		enhancer.setCallback(this);
		return enhancer.create();
	}

	@Override
	public Object intercept(Object obj, Method method, Object[] arg2,
			MethodProxy proxy) throws Throwable {
		// TODO Auto-generated method stub

		PerformanceMonitor.begin(method.getName());
		Object result = proxy.invokeSuper(obj, arg2);
		PerformanceMonitor.end(method.getName());

		return result;
	}

}
