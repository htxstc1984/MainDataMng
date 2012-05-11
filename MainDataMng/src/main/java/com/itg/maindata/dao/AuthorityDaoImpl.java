package com.itg.maindata.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceUnit;

import com.itg.maindata.domain.SyAuthority;

public class AuthorityDaoImpl implements AuthorityDao {

	@PersistenceUnit
	private EntityManager em;

	@Override
	public void addAuth(SyAuthority auth) {
		// TODO Auto-generated method stub
		em.persist(auth);
	}

	@Override
	public void removeAuth(String pk_auth) {
		// TODO Auto-generated method stub
		em.remove(getAuth(pk_auth));
	}

	@Override
	public SyAuthority getAuth(String pk_auth) {
		// TODO Auto-generated method stub
		return em.find(SyAuthority.class, pk_auth);
	}

	@Override
	public void updateAuth(SyAuthority auth) {
		// TODO Auto-generated method stub
		em.merge(auth);
	}

	@Override
	public List<SyAuthority> getAuths(String sWhere) {
		// TODO Auto-generated method stub
		return em.createQuery("select s from SyAuthority s where " + sWhere,
				SyAuthority.class).getResultList();
	}

}
