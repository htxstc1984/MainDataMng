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

import com.itg.maindata.domain.SyAuthority;
import com.itg.maindata.domain.SyMenu;
import com.itg.maindata.domain.SyUser;
import com.itg.maindata.service.AuthorityService;
import com.itg.maindata.service.MenuService;
import com.itg.maindata.service.UserService;
import com.itg.maindata.web.jsonvo.JsonAuthoritys;
import com.itg.maindata.web.jsonvo.JsonRAuthMenuUI;
import com.itg.maindata.web.jsonvo.JsonRUserAuthUI;
import com.itg.maindata.web.vo.FormSubmitRS;
import com.itg.maindata.web.vo.RAuthMenuUI;
import com.itg.maindata.web.vo.RUserAuthUI;

@Controller
public class AuthorityController {
	@Autowired
	private UserService userService;

	@Autowired
	private AuthorityService authorityService;

	@Autowired
	private MenuService menuService;

	@RequestMapping(value = "/getAuths.html")
	public String getAllAuths() {
		return "auth/authList";
	}

	@RequestMapping(value = "/getAuthList.html")
	@ResponseBody
	public Object getAuthList() {
		JsonAuthoritys auths = new JsonAuthoritys();
		auths.setAuths(authorityService.getAuths("1=1"));
		return auths;
	}

	@RequestMapping(value = "/getAuth/{id}")
	public ModelAndView getUser(@PathVariable("id") String id) {
		ModelAndView mav = new ModelAndView("auth/auth");

		SyAuthority auth = authorityService.getAuth(id);

		if (auth == null) {
			auth = new SyAuthority();
		}

		mav.addObject("auth", auth);

		return mav;
	}

	@RequestMapping(value = "/addAuthCommand.html")
	@ResponseBody
	public Object addCommand(SyAuthority auth) {
		FormSubmitRS rs = new FormSubmitRS();
		boolean falg = authorityService.addAuth(auth);
		if (falg) {
			rs.setSuccess(true);
			return rs;
		}
		rs.setSuccess(false);
		return rs;
	}

	@RequestMapping(value = "/getRAuthMenu.html")
	@ResponseBody
	public Object getRAuthMenu(@RequestParam String pkAuth) {
		List<SyMenu> menuList = menuService.getMenus("1=1");
		JsonRAuthMenuUI jram = new JsonRAuthMenuUI();
		List<RAuthMenuUI> rams = new ArrayList<RAuthMenuUI>();

		for (SyMenu menu : menuList) {
			RAuthMenuUI ram = new RAuthMenuUI();
			ram.setPkMenu(menu.getPkMenu());
			ram.setName(menu.getName());
			ram.setCode(menu.getCode());
			ram.setUrl(menu.getUrl());
			ram.setPkParent(menu.getPkParent());
			ram.setIsfolder(menu.getIsfolder());
			ram.setHaveMenu(authorityService.findRAuthMenu(pkAuth,
					menu.getPkMenu()));
			rams.add(ram);

		}
		jram.setRams(rams);
		return jram;
	}

	@RequestMapping(value = "/updateAuth.html")
	@ResponseBody
	public Object updateCommand(HttpServletRequest request,
			HttpServletResponse res, SyAuthority auth) throws IOException {
		FormSubmitRS rs = new FormSubmitRS();
		if (auth.getName() == null || auth.getName().equalsIgnoreCase("")) {
			rs.setSuccess(false);
		}
		String pkMenus = request.getParameter("pkMenus");
		System.out.println(pkMenus);
		boolean falg = authorityService.updateAuth(auth, pkMenus);
		if (falg) {
			rs.setSuccess(true);
		}
		return rs;
	}
}
