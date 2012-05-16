package com.itg.maindata.web.jsonvo;

import java.io.Serializable;
import java.util.List;

import com.itg.maindata.web.vo.RUserAuthUI;

public class JsonRUserAuthUI implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<RUserAuthUI> ruas;

	public List<RUserAuthUI> getRuas() {
		return ruas;
	}

	public void setRuas(List<RUserAuthUI> ruas) {
		this.ruas = ruas;
	}
}
