package com.itg.maindata.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

}
