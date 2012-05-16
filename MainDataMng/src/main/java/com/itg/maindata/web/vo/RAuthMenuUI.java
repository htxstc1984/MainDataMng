package com.itg.maindata.web.vo;

import java.io.Serializable;

import com.itg.maindata.domain.SyMenu;

public class RAuthMenuUI extends SyMenu implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private boolean haveMenu;

	public boolean isHaveMenu() {
		return haveMenu;
	}

	public void setHaveMenu(boolean haveMenu) {
		this.haveMenu = haveMenu;
	}

}
