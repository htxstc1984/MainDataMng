package com.itg.maindata.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.itg.maindata.domain.RUserAuth;
import com.itg.maindata.domain.SyUser;

public class UserDaoImpl implements UserDao {

	@PersistenceContext
	private EntityManager em;

	@Override
	public void addUser(SyUser user) {
		// TODO Auto-generated method stub
		em.persist(user);
	}

	@Override
	public void removeUser(String pk_user) {
		// TODO Auto-generated method stub
		em.remove(getUser(pk_user));
	}

	@Override
	public SyUser getUser(String pk_user) {
		// TODO Auto-generated method stub
		return em.find(SyUser.class, pk_user);
	}

	@Override
	public void updateUser(SyUser user) {
		// TODO Auto-generated method stub
		em.merge(user);
	}

	@Override
	public List<SyUser> getUsers(String sWhere) {
		// TODO Auto-generated method stub

		return em.createQuery("select s from SyUser s where " + sWhere,
				SyUser.class).getResultList();
	}

	@Override
	public boolean findRUserAuth(String pk_user, String pk_auth) {
		// TODO Auto-generated method stub
		Long count = em.createQuery(
				"select count(a) from RUserAuth a where pkUser='" + pk_user
						+ "' and pkAuthority='" + pk_auth + "'", Long.class)
				.getSingleResult();
		if (count.intValue() > 0) {
			return true;
		}
		return false;
	}

	@Override
	public void removeAuths(String pk_user) {
		// TODO Auto-generated method stub
		em.createQuery("delete from RUserAuth where pkUser='" + pk_user + "'")
				.executeUpdate();
	}

	@Override
	public void createAuths(String pk_user, String pk_auths) {
		// TODO Auto-generated method stub
		String[] auths = pk_auths.split(";");
		// String inWhere = "";
		for (String auth : auths) {
			// inWhere = inWhere + "'" + auth + "',";
			RUserAuth rua = new RUserAuth();
			rua.setPkUser(pk_user);
			rua.setPkAuthority(auth);
			em.persist(rua);
		}
		// if (!inWhere.equalsIgnoreCase("")) {
		// inWhere = inWhere.substring(0, inWhere.length() - 1);
		// }
	}
}
