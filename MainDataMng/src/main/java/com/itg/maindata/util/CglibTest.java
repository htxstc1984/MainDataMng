package com.itg.maindata.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * <p>
 * Title: CglibTest.java
 * </p>
 * <p>
 * E-Mail: 176291935@qq.com
 * </p>
 * <p>
 * QQ: 176291935
 * </p>
 * <p>
 * Http: iaiai.iteye.com
 * </p>
 * <p>
 * Create time: 2011-11-16
 * </p>
 * 
 * @author 丸子
 * @version 0.0.1
 */
public class CglibTest {

	public static void main(String[] args) throws Exception {
		List<String> list = new ArrayList<String>();
		list.add("测试1");
		list.add("测试2");

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("test1", "map value1");
		map.put("test2", "map value2");

		// 设置类成员属性
		Map propertyMap = new HashMap();
		propertyMap.put("id", Class.forName("java.lang.Integer"));
		propertyMap.put("name", Class.forName("java.lang.String"));
		propertyMap.put("address", Class.forName("java.lang.String"));
		propertyMap.put("age", Class.forName("java.lang.Long"));
		propertyMap.put("list", Class.forName("java.util.List"));
		propertyMap.put("map", Class.forName("java.util.Map"));

		// 生成动态 Bean
		CglibBean bean = new CglibBean("com.itg.maindata.domain.SyAuthority", propertyMap);

		// 给 Bean 设置值
		bean.setValue("id", new Integer(123));
		bean.setValue("name", "454");
		bean.setValue("address", "789");
		bean.setValue("age", new Long(321));
		bean.setValue("list", list);
		bean.setValue("map", map);

		// 从 Bean 中获取值，当然了获得值的类型是 Object
		System.out.println("  >> class      = " + bean.getObject().getClass());
		System.out.println("  >> id      = " + bean.getValue("id"));
		System.out.println("  >> name    = " + bean.getValue("name"));
		System.out.println("  >> address = " + bean.getValue("address"));
		System.out.println("  >> age = " + bean.getValue("age"));
		System.out.println("  >> list instanceof = "
				+ (bean.getValue("list") instanceof List));
		System.out.println("  >> list.size = "
				+ ((List) bean.getValue("list")).size());
		System.out.println("  >> list[0] = "
				+ ((List) bean.getValue("list")).get(0));
		System.out.println("  >> map instanceof = "
				+ (bean.getValue("map") instanceof Map));
		System.out.println("  >> map.size = "
				+ ((Map) bean.getValue("map")).size());
		System.out.println("  >> map.value = "
				+ ((Map) bean.getValue("map")).get("test1"));

		// 获得bean的实体
		Object object = bean.getObject();

		// 通过反射查看所有方法名
		Class clazz = object.getClass();

		Method[] methods = clazz.getDeclaredMethods();
		for (int i = 0; i < methods.length; i++) {
			System.out.println(methods[i].getName());
		}

		System.out.println("********************");
		test(object);
	}

	private static void test(Object obj) throws Exception {
		// 设置类成员属性
		HashMap propertyMap = new HashMap();
		propertyMap.put("id", Class.forName("java.lang.Integer"));
		propertyMap.put("name", Class.forName("java.lang.String"));
		propertyMap.put("address", Class.forName("java.lang.String"));
		propertyMap.put("object", obj.getClass());

		// 生成动态 Bean
		CglibBean bean = new CglibBean("com.itg.maindata.domain.SyAuthority", propertyMap);

		// 给 Bean 设置值
		bean.setValue("id", new Integer(1232));
		bean.setValue("name", "4542");
		bean.setValue("address", "7892");
		bean.setValue("object", obj);

		// 从 Bean 中获取值，当然了获得值的类型是 Object
		System.out.println("  >> class      = " + bean.getObject().getClass());
		System.out.println("  >> id      = " + bean.getValue("id"));
		System.out.println("  >> name    = " + bean.getValue("name"));
		System.out.println("  >> address = " + bean.getValue("address"));
		System.out.println("  >> object = " + bean.getValue("object"));
		System.out.println("  >> object instanceof String = "
				+ (bean.getValue("object") instanceof String));
		System.out.println("  >> object instanceof Object= "
				+ (bean.getValue("object") instanceof Object));

		Class cls = bean.getObject().getClass();

		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
		Field[] fields = cls.getDeclaredFields();
		for (Field field : fields) {
			System.out.println(field.getName());
		}
	}

}