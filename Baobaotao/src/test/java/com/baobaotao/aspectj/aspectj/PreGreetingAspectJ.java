package com.baobaotao.aspectj.aspectj;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class PreGreetingAspectJ {
	
	@Before("execution(* greet*(..))")
	public void beforeGreeting()
	{
		System.out.println("before greeting");
	}
}
