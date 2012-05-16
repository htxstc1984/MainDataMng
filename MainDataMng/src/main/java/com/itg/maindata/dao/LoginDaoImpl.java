package com.itg.maindata.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class LoginDaoImpl implements LoginDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public boolean validUser(String userid, String password) {
		// TODO Auto-generated method stub

		Long count = em.createQuery(
				"select count(a) from SyUser a where userid='" + userid
						+ "' and password='" + password + "'", Long.class)
				.getSingleResult();

		if (count > 0) {
			return true;
		}
		return false;
	}

}
