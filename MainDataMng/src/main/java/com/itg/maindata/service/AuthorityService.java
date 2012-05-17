package com.itg.maindata.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.itg.maindata.dao.AuthorityDao;
import com.itg.maindata.domain.RUserAuth;
import com.itg.maindata.domain.SyAuthority;

public class AuthorityService {

	@Autowired
	public AuthorityDao authorityDao;

	@Transactional(propagation = Propagation.REQUIRED)
	public boolean addAuth(SyAuthority auth) {
		try {
			authorityDao.addAuth(auth);
		} catch (Exception e) {
			return false;
			// TODO: handle exception
		}
		return true;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public boolean removeAuth(String pk_auth) {
		try {
			authorityDao.removeAuth(pk_auth);
			authorityDao.removeMenus(pk_auth);
		} catch (Exception e) {
			return false;
			// TODO: handle exception
		}
		return true;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public SyAuthority getAuth(String pk_auth) {
		return authorityDao.getAuth(pk_auth);

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public boolean updateAuth(SyAuthority auth, String pk_menus) {
		try {
			authorityDao.updateAuth(auth);
			authorityDao.removeMenus(auth.getPkAuthority());
			authorityDao.createMenus(auth.getPkAuthority(), pk_menus);
		} catch (Exception e) {
			return false;
			// TODO: handle exception
		}
		return true;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<SyAuthority> getAuths(String sWhere) {
		return authorityDao.getAuths(sWhere);

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public boolean findRAuthMenu(String pk_auth, String pk_menu) {
		return authorityDao.findRAuthMenu(pk_auth, pk_menu);
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<RUserAuth> getAuthsByUser(String pk_users) {
		return authorityDao.getAuthsByUser(pk_users);
	}
}
