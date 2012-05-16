package com.itg.maindata.web.jsonvo;

import java.io.Serializable;
import java.util.List;

import com.itg.maindata.domain.SyUser;

public class JsonUsers implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<SyUser> users;

	public List<SyUser> getUsers() {
		return users;
	}

	public void setUsers(List<SyUser> users) {
		this.users = users;
	}
}
