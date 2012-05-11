package com.itg.maindata.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
		em.persist(user);
	}

	@Override
	public List<SyUser> getUsers(String sWhere) {
		// TODO Auto-generated method stub
		
		return em.createQuery("select s from SyUser s where " + sWhere,
				SyUser.class).getResultList();
	}

}
