package com.itg.maindata.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.itg.maindata.dao.LoginDao;

public class LoginService {

	@Autowired
	private LoginDao loginDao;

	public boolean validUser(String userid, String password) {
		return loginDao.validUser(userid, password);
	}
}
