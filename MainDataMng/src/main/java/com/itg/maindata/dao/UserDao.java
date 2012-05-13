package com.itg.maindata.dao;

import java.util.List;

import com.itg.maindata.domain.SyUser;

public interface UserDao {
	public void addUser(SyUser user);

	public void removeUser(String pk_user);

	public SyUser getUser(String pk_user);

	public void updateUser(SyUser user);

	public List<SyUser> getUsers(String sWhere);

	public boolean findRUserAuth(String pk_user, String pk_auth);

	public void removeAuths(String pk_user);

	public void createAuths(String pk_user, String pk_auths);
}
