package com.itg.maindata.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.itg.maindata.dao.UtilDao;

public class UtilService {

	@Autowired
	UtilDao utilDao;

	@Transactional(propagation = Propagation.REQUIRED)
	public Object getVOByPk(String className, String pk) throws Exception {
		// TODO Auto-generated method stub
		return utilDao.getVOByPk(className, pk);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public Object[] getVOsByRefPk(String className, String midClassName,
			String refFieldName, String pk) throws Exception {
		return utilDao.getVOsByRefPk(className, midClassName, refFieldName, pk);
	}
}
