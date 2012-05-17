package com.itg.maindata.dao;

import java.util.List;

import com.itg.maindata.domain.RAuthMenu;
import com.itg.maindata.domain.SyMenu;

public interface MenuDao {
	public void addMenu(SyMenu menu);

	public void removeMenu(String pk_menu);

	public SyMenu getMenu(String pk_menu);

	public void updateMenu(SyMenu menu);

	public List<SyMenu> getMenus(String sWhere);

	public List<RAuthMenu> getMenusByAuth(String pk_auths);

	public List<SyMenu> getAllMenus();
}
