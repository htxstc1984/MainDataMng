package com.baobaotao.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baobaotao.dao.LoginLogDao;
import com.baobaotao.dao.UserDao;
import com.baobaotao.domain.LoginLog;
import com.baobaotao.domain.User;

@Service
public class UserService {
	@Autowired
	private UserDao userDao;

	@Autowired
	private LoginLogDao loginLogDao;

	public boolean hasMatchUser(String userName, String password) {
		int count = userDao.getMatchCount(userName, password);
		return count > 0;
	}

	public User findUseByUserName(String userName) {
		return userDao.findUserByUserName(userName);
	}

	public void loginSuccess(User user) {
		user.setCredits(user.getCredits() + 5);
		LoginLog loginLog = new LoginLog();
		loginLog.setUserId(user.getUserid());
		loginLog.setIp(user.getLastlp());
		loginLog.setLoginDate(user.getLastVisit());

		userDao.updateLoginInfo(user);
		loginLogDao.insertLoginLog(loginLog);

	}
}
