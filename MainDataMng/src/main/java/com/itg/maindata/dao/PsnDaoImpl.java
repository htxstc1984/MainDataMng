package com.itg.maindata.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.itg.maindata.domain.MdPsn;

public class PsnDaoImpl implements PsnDao {

	@PersistenceContext
	EntityManager em;

	@Override
	public void addPsn(MdPsn psn) {
		// TODO Auto-generated method stub
		em.persist(psn);
	}

	@Override
	public void removePsn(String pk_psn) {
		// TODO Auto-generated method stub
		em.remove(getPsn(pk_psn));
	}

	@Override
	public MdPsn getPsn(String pk_psn) {
		// TODO Auto-generated method stub
		return em.find(MdPsn.class, pk_psn);
	}

	@Override
	public void updatePsn(MdPsn psn) {
		// TODO Auto-generated method stub
		em.merge(psn);
	}

	@Override
	public List<MdPsn> getPsns(String sql) {
		// TODO Auto-generated method stub
		return em.createQuery(sql, MdPsn.class).getResultList();
	}

	@Override
	public List<MdPsn> getPsnsByDept(String pk_depts) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<MdPsn> getAllPsns() {
		// TODO Auto-generated method stub
		return em.createQuery("select a from MdPsn a", MdPsn.class)
				.getResultList();
	}

}
