package com.baobaotao.editor;

public class Boss {
	private Car car;

	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}
	
	public void init()
	{
		System.out.println("init BOSS");
	}
}
