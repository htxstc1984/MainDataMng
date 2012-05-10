package com.itg.maindata.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.itg.maindata.domain.SyUser;
import com.itg.maindata.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/addUser.html")
	public String addUser() {
		return "user/userAdd";
	}

	@RequestMapping(value = "/addCommand.html")
	public ModelAndView addCommand(SyUser user) {
		boolean falg = userService.addUser(user);
		if (falg) {
			ModelAndView mav = new ModelAndView("user/success");
			mav.addObject(user);
			return mav;
		}
		return new ModelAndView("user/userAdd", "error", "用户名或者密码错误");
	}
}
