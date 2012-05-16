package com.itg.maindata.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.itg.maindata.dao.AuthorityDao;
import com.itg.maindata.domain.SyAuthority;
import com.itg.maindata.domain.SyUser;
import com.itg.maindata.service.UserService;
import com.itg.maindata.web.jsonvo.JsonRUserAuthUI;
import com.itg.maindata.web.jsonvo.JsonUsers;
import com.itg.maindata.web.vo.FormSubmitRS;
import com.itg.maindata.web.vo.RUserAuthUI;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	private AuthorityDao authorityService;

	@Autowired
	private AuthorityDao menuService;

	@RequestMapping(value = "/test.html")
	public String testExtjs() {
		return "user/testExtjs";
	}

	@RequestMapping(value = "/addCommand.html")
	@ResponseBody
	public Object addCommand(SyUser user) {
		FormSubmitRS rs = new FormSubmitRS();
		boolean falg = userService.addUser(user);
		if (falg) {
			rs.setSuccess(true);
			return rs;
		}
		rs.setSuccess(false);
		return rs;
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
		msgmav.addObject("message", "没有用户");
		return msgmav;
	}

	@RequestMapping(value = "/getUser/{id}")
	public ModelAndView getUser(@PathVariable("id") String id) {
		ModelAndView mav = new ModelAndView("user/user");

		SyUser user = userService.getUser(id);

		if (user == null) {
			user = new SyUser();
		}

		mav.addObject("user", user);

		return mav;
	}

	@RequestMapping(value = "/getRUserAuth.html")
	@ResponseBody
	public Object getRUserAuth(@RequestParam String pkUser) {
		List<SyAuthority> authList = authorityService.getAuths("1=1");

		JsonRUserAuthUI jrua = new JsonRUserAuthUI();
		List<RUserAuthUI> ruas = new ArrayList<RUserAuthUI>();
		for (SyAuthority auth : authList) {
			RUserAuthUI rua = new RUserAuthUI();
			rua.setPkAuthority(auth.getPkAuthority());
			rua.setName(auth.getName());
			rua.setBz(auth.getBz());
			// rua.setSyAuthority(auth);
			rua.setHaveAuth(userService.findRUserAuth(pkUser,
					auth.getPkAuthority()));
			ruas.add(rua);
		}
		jrua.setRuas(ruas);
		return jrua;
	}

	@RequestMapping(value = "/updateUser.html")
	@ResponseBody
	public Object updateCommand(HttpServletRequest request,
			HttpServletResponse res, SyUser user) throws IOException {
		FormSubmitRS rs = new FormSubmitRS();
		if (user.getUserid() == null || user.getUserid().equalsIgnoreCase("")) {
			rs.setSuccess(false);
		}
		String pkAuths = request.getParameter("pkAuths");
		System.out.println(pkAuths);
		boolean falg = userService.updateUser(user, pkAuths);
		if (falg) {
			rs.setSuccess(true);
		}
		return rs;
	}


	@RequestMapping(value = "/getUserList2.html")
	@ResponseBody
	public Object getUserList() {
		JsonUsers users = new JsonUsers();
		users.setUsers(userService.getUsers("1=1"));
		return users;
	}
}
