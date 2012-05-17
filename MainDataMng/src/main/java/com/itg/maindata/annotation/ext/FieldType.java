package com.itg.maindata.annotation.ext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface FieldType {
	public abstract String value();

	public abstract String inputType();
}
