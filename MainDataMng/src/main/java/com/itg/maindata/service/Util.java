package com.itg.maindata.service;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Id;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.itg.maindata.annotation.ext.AllowBlank;
import com.itg.maindata.annotation.ext.DataType;
import com.itg.maindata.annotation.ext.FieldType;
import com.itg.maindata.annotation.ext.Sort;
import com.itg.maindata.annotation.ext.Title;
import com.itg.maindata.annotation.ext.Width;
import com.itg.maindata.domain.SyMenu;
import com.itg.maindata.web.jsonvo.JsonColumModels;
import com.itg.maindata.web.vo.ColumModelVO;

public class Util {

	public Document transMenuToXml(List<SyMenu> menus) {
		Map<String, Element> elements = new HashMap<String, Element>();
		Document doc = DocumentHelper.createDocument();

		Element root = doc.addElement("菜单");
		root.addAttribute("expanded", "true");
		for (SyMenu menu : menus) {
			if (menu.getPkParent() == null
					|| menu.getPkParent().equalsIgnoreCase("")) {
				Element folder = root.addElement(menu.getName());
				folder.addAttribute("expanded", "true");
				folder.addAttribute("id", menu.getPkMenu());
				elements.put(menu.getPkMenu(), folder);
			} else {
				if (elements.containsKey(menu.getPkParent())) {
					Element parent = elements.get(menu.getPkParent());
					Element children = parent.addElement(menu.getName());
					if (menu.getIsfolder() == 1) {
						children.addAttribute("expanded", "true");
						children.addAttribute("id", menu.getPkMenu());
					} else {
						children.addAttribute("url", menu.getUrl());
						children.addAttribute("id", menu.getPkMenu());
					}
					elements.put(menu.getPkMenu(), children);
				}
			}
		}

		return doc;
	}

	public JsonColumModels getColumsByClass(String className, Object instance)
			throws Exception {
		ClassLoader loader = Thread.currentThread().getContextClassLoader();
		Class clazz = loader.loadClass(className);
		Constructor cons = clazz.getDeclaredConstructor((Class[]) null);
		cons.newInstance();
		Method[] methods = clazz.getMethods();
		JsonColumModels jcols = new JsonColumModels();
		List<ColumModelVO> cols = new ArrayList<ColumModelVO>();
		for (Method method : methods) {
			if (method.getName().startsWith("get")
					&& !method.getName().equalsIgnoreCase("getClass")) {
				boolean newIdFlag = false;
				ColumModelVO col = new ColumModelVO();
				char firstLetter = method.getName().charAt(3);
				char lowerLetter = Character.toLowerCase(firstLetter);
				String first = String.valueOf(new char[] { lowerLetter });
				String colName = first
						+ method.getName().substring(4,
								method.getName().length());
				col.setFieldName(colName);
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
					} else if (anno.annotationType() == Id.class) {
						jcols.setPkFieldName(colName);
					}
				}

				if (instance != null) {
					Object valueObj = method.invoke(instance, new Object[] {});

					if (valueObj != null) {
						String value = String.valueOf(valueObj);
						col.setValue(value);
					} else {
						col.setValue("");
					}
				}
				if (col.getTitle().equalsIgnoreCase("")) {
					col.setTitle(col.getFieldName());
				}
				if (!newIdFlag) {
					cols.add(col);
				}

			}
			Class type = method.getReturnType();
			// method.invoke(obj, args)
		}
		jcols.setCols(cols);
		return jcols;
	}
}
