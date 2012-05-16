package com.itg.maindata.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.itg.maindata.dao.AuthorityDao;
import com.itg.maindata.service.UserService;
import com.itg.maindata.web.jsonvo.JsonAuthoritys;

@Controller
public class AuthorityController {
	@Autowired
	private UserService userService;

	@Autowired
	private AuthorityDao authorityService;

	@Autowired
	private AuthorityDao menuService;

	@RequestMapping(value = "/getAuths.html")
	public String getAllUser() {
		return "auth/authList";
	}

	@RequestMapping(value = "/getAuthList.html")
	@ResponseBody
	public Object getAuthList() {
		JsonAuthoritys auths = new JsonAuthoritys();
		auths.setAuths(authorityService.getAuths("1=1"));
		return auths;
	}
}
