package com.baobaotao.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class RequestProxy implements InvocationHandler {

	Object obj;

	public RequestProxy(Object obj) {
		this.obj = obj;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		// TODO Auto-generated method stub

		return method.invoke(obj, args);
	}

}
