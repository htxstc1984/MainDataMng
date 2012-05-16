package com.itg.maindata.dao;

import java.util.List;

import com.itg.maindata.domain.SyAuthority;
import com.itg.maindata.domain.SyMenu;

public interface AuthorityDao {

	public void addAuth(SyAuthority auth);

	public void removeAuth(String pk_auth);

	public SyAuthority getAuth(String pk_auth);

	public void updateAuth(SyAuthority auth);

	public List<SyAuthority> getAuths(String sWhere);

	public List<SyMenu> getAllMenus();

	public boolean findRAuthMenu(String pk_auth, String pk_menu);

	public void removeMenus(String pk_auth);

	public void createMenus(String pk_auth, String pk_menus);
}
