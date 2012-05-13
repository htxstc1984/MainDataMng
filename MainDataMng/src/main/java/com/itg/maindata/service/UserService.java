package com.itg.maindata.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.itg.maindata.dao.UserDao;
import com.itg.maindata.domain.SyUser;

@Service
public class UserService {

	@Autowired
	public UserDao userDao;

	@Transactional(propagation = Propagation.REQUIRED)
	public boolean addUser(SyUser user) {
		try {
			userDao.addUser(user);
		} catch (Exception e) {
			return false;
			// TODO: handle exception
		}
		return true;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public boolean removeUser(String pk_user) {
		try {
			userDao.removeUser(pk_user);
			userDao.removeAuths(pk_user);
		} catch (Exception e) {
			return false;
			// TODO: handle exception
		}
		return true;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public SyUser getUser(String pk_user) {
		return userDao.getUser(pk_user);

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public boolean updateUser(SyUser user, String pk_auths) {
		try {
			userDao.updateUser(user);
			userDao.removeAuths(user.getPkUser());
			userDao.createAuths(user.getPkUser(), pk_auths);
		} catch (Exception e) {
			return false;
			// TODO: handle exception
		}
		return true;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<SyUser> getUsers(String sWhere) {
		return userDao.getUsers(sWhere);

	}

	public boolean findRUserAuth(String pk_user, String pk_auth) {
		return userDao.findRUserAuth(pk_user, pk_auth);
	}
}
