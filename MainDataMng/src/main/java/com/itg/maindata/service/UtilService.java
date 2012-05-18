package com.itg.maindata.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.itg.maindata.dao.UtilDao;

public class UtilService {

	@Autowired
	UtilDao utilDao;

	public Object getVOByPk(String className, String pk) throws Exception {
		// TODO Auto-generated method stub
		return utilDao.getVOByPk(className, pk);
	}
}
