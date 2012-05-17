package com.itg.maindata.annotation.ext;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Width {
	public abstract int value();
}
