package com.itg.maindata.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.itg.maindata.dao.PsnDao;
import com.itg.maindata.domain.MdPsn;

public class PsnService {
	@Autowired
	PsnDao psnDao;

	@Transactional(propagation = Propagation.REQUIRED)
	public void addPsn(MdPsn psn) {
		psnDao.addPsn(psn);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public boolean removePsn(String pk_psn) {
		try {
			psnDao.removePsn(pk_psn);
		} catch (Exception e) {
			return false;
			// TODO: handle exception
		}
		return true;

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public MdPsn getPsn(String pk_psn) {
		return psnDao.getPsn(pk_psn);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public boolean updatePsn(MdPsn psn) {
		try {
			psnDao.updatePsn(psn);
		} catch (Exception e) {
			return false;
			// TODO: handle exception
		}
		return true;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<MdPsn> getPsns(String sql) {
		return psnDao.getPsns(sql);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<MdPsn> getPsnsByDept(String pk_depts) {
		return psnDao.getPsnsByDept(pk_depts);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<MdPsn> getAllPsns() {
		return psnDao.getAllPsns();
	}
}
