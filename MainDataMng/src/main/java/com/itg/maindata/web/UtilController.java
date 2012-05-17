package com.itg.maindata.web;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itg.maindata.annotation.ext.AllowBlank;
import com.itg.maindata.annotation.ext.DataType;
import com.itg.maindata.annotation.ext.FieldType;
import com.itg.maindata.annotation.ext.Sort;
import com.itg.maindata.annotation.ext.Title;
import com.itg.maindata.annotation.ext.Width;
import com.itg.maindata.web.jsonvo.JsonColumModels;
import com.itg.maindata.web.vo.ColumModelVO;

@Controller
public class UtilController {

	@RequestMapping(value = "/getColums.html")
	@ResponseBody
	public Object getColumsByClass(@RequestParam String className)
			throws ClassNotFoundException, SecurityException,
			NoSuchMethodException {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Class clazz = loader.loadClass(className);
		// Constructor cons = clazz.getDeclaredConstructor((Class[]) null);
		Method[] methods = clazz.getMethods();
		JsonColumModels jcols = new JsonColumModels();
		List<ColumModelVO> cols = new ArrayList<ColumModelVO>();
		for (Method method : methods) {
			if (method.getName().startsWith("get")) {
				ColumModelVO col = new ColumModelVO();
				char firstLetter = method.getName().charAt(3);
				char lowerLetter = Character.toLowerCase(firstLetter);
				String first = String.valueOf(new char[] { lowerLetter });
				col.setFieldName(first
						+ method.getName().substring(4,
								method.getName().length()));
				Annotation[] annos = method.getAnnotations();
				for (Annotation anno : annos) {
					if (anno.annotationType() == Width.class) {
						col.setWidth(method.getAnnotation(Width.class).value());
					} else if (anno.annotationType() == AllowBlank.class) {
						col.setAllowBlank(true);
					} else if (anno.annotationType() == FieldType.class) {
						col.setExtType(method.getAnnotation(FieldType.class)
								.value());
						if (method.getAnnotation(FieldType.class).inputType()
								.equalsIgnoreCase("")) {
							col.setInputType(method.getAnnotation(
									FieldType.class).inputType());
						}
					} else if (anno.annotationType() == DataType.class) {
						col.setDataType(method.getAnnotation(DataType.class)
								.value());
					} else if (anno.annotationType() == Sort.class) {
						col.setSortable(true);
					} else if (anno.annotationType() == Title.class) {
						col.setTitle(method.getAnnotation(Title.class).value());
					}
				}
				cols.add(col);
			}
			Class type = method.getReturnType();
		}
		jcols.setCols(cols);
		return jcols;
	}
}
