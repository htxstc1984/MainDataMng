package com.baobaotao.advisor;

import java.lang.reflect.Method;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;

public class GreetingAdvisor extends StaticMethodMatcherPointcutAdvisor {

	@Override
	public boolean matches(Method arg0, Class<?> arg1) {
		// TODO Auto-generated method stub
		return "greetTo".equals(arg0.getName());
	}


	public ClassFilter getClassFilter() {
		// TODO Auto-generated method stub
		return new ClassFilter() {

			@Override
			public boolean matches(Class<?> class1) {
				// TODO Auto-generated method stub
				return Waiter.class.isAssignableFrom(class1);
			}
		};
	}

}
