package com.itg.maindata.web.jsonvo;

import java.io.Serializable;
import java.util.List;

import com.itg.maindata.domain.SyMenu;

public class JsonMenus implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<SyMenu> menus;

	public List<SyMenu> getMenus() {
		return menus;
	}

	public void setMenus(List<SyMenu> menus) {
		this.menus = menus;
	}

}
