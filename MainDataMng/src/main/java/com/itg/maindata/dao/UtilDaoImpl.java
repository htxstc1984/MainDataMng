package com.itg.maindata.dao;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.itg.maindata.util.Util;

public class UtilDaoImpl implements UtilDao {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public Object getVOByPk(String className, String pk) throws Exception {
		// TODO Auto-generated method stub

		Class clazz = Class.forName(className);
		return em.find(clazz, pk);
	}

	// className是目标类，midClassName是关联类，refFieldName是主类主键
	@SuppressWarnings("unchecked")
	public Object[] getVOsByRefPk(String className, String midClassName,
			String refFieldName, String pk) throws Exception {
		Util util = new Util();
		Class clazz1 = Class.forName(className);
		Class clazz2 = Class.forName(midClassName);
		String pkClass = util.getPkFieldNameByClassName(className);
		Object[] result_mid = null;
		Object[] result = null;
		ArrayList list_result = new ArrayList();
		List list_mid = em.createQuery(
				"select e from " + midClassName + " e where " + refFieldName
						+ "='" + pk + "'", clazz2).getResultList();
		if (!list_mid.isEmpty()) {
			result_mid = new Object[list_mid.size()];
			list_mid.toArray(result_mid);
			for (Object mid : list_mid) {

				Method[] methods = clazz2.getMethods();
				for (Method method : methods) {
					if (method.getName().startsWith("get")
							&& !method.getName().equalsIgnoreCase("getClass")) {
						char firstLetter = method.getName().charAt(3);
						char lowerLetter = Character.toLowerCase(firstLetter);
						String first = String
								.valueOf(new char[] { lowerLetter });
						String colName = first
								+ method.getName().substring(4,
										method.getName().length());
						if (colName.equalsIgnoreCase(pkClass)) {
							Object valueObj = method.invoke(mid,
									new Object[] {});
							list_result.add(em.find(clazz1, (String) valueObj));
						}
					}
				}
			}
			if (!list_result.isEmpty()) {
				result = new Object[list_result.size()];
				list_result.toArray(result);
				return result;
			}
		}
		return null;
	}

}
