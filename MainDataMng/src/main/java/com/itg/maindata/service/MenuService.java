package com.itg.maindata.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.itg.maindata.dao.MenuDao;
import com.itg.maindata.domain.SyMenu;

public class MenuService {
	@Autowired
	public MenuDao menuDao;

	@Transactional(propagation = Propagation.REQUIRED)
	public boolean addMenu(SyMenu menu) {
		try {
			menuDao.addMenu(menu);
		} catch (Exception e) {
			return false;
			// TODO: handle exception
		}
		return true;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public boolean removeMenu(String pk_menu) {
		try {
			menuDao.removeMenu(pk_menu);
		} catch (Exception e) {
			return false;
			// TODO: handle exception
		}
		return true;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public SyMenu getMenu(String pk_menu) {
		return menuDao.getMenu(pk_menu);

	}

	@Transactional(propagation = Propagation.REQUIRED)
	public boolean updateMenu(SyMenu menu) {
		try {
			menuDao.updateMenu(menu);
		} catch (Exception e) {
			return false;
			// TODO: handle exception
		}
		return true;
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public List<SyMenu> getMenus(String sWhere) {
		return menuDao.getMenus(sWhere);

	}
}
