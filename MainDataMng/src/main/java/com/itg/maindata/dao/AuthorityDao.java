package com.itg.maindata.dao;

import java.util.List;

import com.itg.maindata.domain.SyAuthority;;

public interface AuthorityDao {
	
	public void addAuth(SyAuthority auth);

	public void removeAuth(String pk_auth);

	public SyAuthority getAuth(String pk_auth);

	public void updateAuth(SyAuthority auth);

	public List<SyAuthority> getAuths(String sWhere);
}
