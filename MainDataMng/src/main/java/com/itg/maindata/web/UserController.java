package com.itg.maindata.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.itg.maindata.dao.AuthorityDao;
import com.itg.maindata.domain.SyAuthority;
import com.itg.maindata.domain.SyUser;
import com.itg.maindata.service.UserService;
import com.itg.maindata.web.vo.RUserAuthUI;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthorityDao authorityDao;

	@RequestMapping(value = "/addUser.html")
	public String addUser() {
		return "user/userAdd";
	}

	@RequestMapping(value = "/addCommand.html")
	public ModelAndView addCommand(SyUser user) {
		boolean falg = userService.addUser(user);
		if (falg) {
			ModelAndView mav = new ModelAndView("user/success");
			mav.addObject("user", user);
			return mav;
		}
		return new ModelAndView("user/userAdd", "error", "添加失败");
	}

	@RequestMapping(value = "/getUsers.html")
	public ModelAndView getAllUser() {
		List<SyUser> userList = userService.getUsers("1=1");
		ModelAndView mav = new ModelAndView("user/userList");
		if (!userList.isEmpty()) {
			// SyUser[] users = new SyUser[userList.size()];
			// users = userList.toArray(users);
			mav.addObject("users", userList);
			return mav;
		}
		ModelAndView msgmav = new ModelAndView("user/message");
		msgmav.addObject("message", "没找到用户");
		return msgmav;
	}

	@RequestMapping(value = "/getUser/{id}")
	public ModelAndView getUser(@PathVariable("id") String id) {
		ModelAndView mav = new ModelAndView("user/user");

		SyUser user = userService.getUser(id);
		mav.addObject("user", user);

		List<SyAuthority> authList = authorityDao.getAuths("1=1");

		List<RUserAuthUI> ruas = new ArrayList<RUserAuthUI>();
		for (SyAuthority auth : authList) {
			RUserAuthUI rua = new RUserAuthUI();
			rua.setSyAuthority(auth);
			rua.setHaveAuth(userService.findRUserAuth(id, auth.getPkAuthority()));
			ruas.add(rua);
		}

		mav.addObject("auths", ruas);

		return mav;
	}

	@RequestMapping(value = "/updateUser.html")
	public ModelAndView updateCommand(SyUser user) {
		boolean falg = userService.updateUser(user);
		if (falg) {
			ModelAndView mav = getAllUser();
			// mav.addObject("user", user);
			return mav;
		}
		return new ModelAndView("user/user", "error", "修改失败");
	}

}
