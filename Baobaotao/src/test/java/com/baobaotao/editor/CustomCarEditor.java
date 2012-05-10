package com.baobaotao.editor;

import java.beans.PropertyEditorSupport;

public class CustomCarEditor extends PropertyEditorSupport {

	public void setAsText(String text) throws IllegalArgumentException {
		// TODO Auto-generated method stub

		if (text == null || text.indexOf(",") == -1) {
			throw new IllegalArgumentException("格式不正确");
		}
		
		String[] objs = text.split(",");
		Car car = new Car();
		car.setBrand(objs[0]);
		car.setColor(objs[1]);
		car.setMaxSpeed(Integer.parseInt(objs[2]));
		
		setValue(car);
//		super.setAsText(text);
	}

}
