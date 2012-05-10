package com.baobaotao.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ReflectTest {
	public static Car initDefultConst() throws Throwable {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Class clazz = loader.loadClass("com.baobaotao.reflect.Car");
		Constructor cons = clazz.getDeclaredConstructor((Class[]) null);
		Car car = (Car) cons.newInstance();
		Method setBrand = clazz.getMethod("setBrand", String.class);
		setBrand.invoke(car, "高尔夫tsi");
		Method setColor = clazz.getMethod("setColor", String.class);
		setColor.invoke(car, "black");
		Method setMaxSpeed = clazz.getMethod("setMaxSpeed", int.class);
		setMaxSpeed.invoke(car, 240);
		return car;
	}

	public static void main(String[] args) throws Throwable {
		Car car = initDefultConst();
		car.introduce();

		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext(
				"bean1.xml");
		Boss boss = (Boss) ctx.getBean("boss");
		boss.getCar().introduce();

	}
}
