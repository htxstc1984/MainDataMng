package com.itg.maindata.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.itg.maindata.domain.MdPsn;
import com.itg.maindata.domain.RAuthMenu;
import com.itg.maindata.domain.RUserAuth;
import com.itg.maindata.domain.SyMenu;
import com.itg.maindata.domain.SyUser;
import com.itg.maindata.service.AuthorityService;
import com.itg.maindata.service.MenuService;
import com.itg.maindata.service.UserService;
import com.itg.maindata.util.Util;
import com.itg.maindata.web.jsonvo.JsonMenus;
import com.itg.maindata.web.vo.FormSubmitRS;
import com.itg.maindata.web.vo.LoginCommand;

@Controller
public class MenuController {
	@Autowired
	private UserService userService;

	@Autowired
	private AuthorityService authorityService;

	@Autowired
	private MenuService menuService;

	@RequestMapping(value = "/getMenus.html")
	public String getAllmenus() {
		return "menu/menuList";
	}

	@RequestMapping(value = "/getMenuList.html")
	@ResponseBody
	public Object getMenuList() {
		JsonMenus menus = new JsonMenus();
		menus.setMenus(menuService.getAllMenus());
		return menus;
	}

	@RequestMapping(value = "/getMenu/{id}")
	public ModelAndView getMenu(@PathVariable("id") String id) {
		ModelAndView mav = new ModelAndView("menu/menu");

		SyMenu menu = menuService.getMenu(id);

		if (menu == null) {
			menu = new SyMenu();
		}

		mav.addObject("menu", menu);

		return mav;
	}

	@RequestMapping(value = "/updateMenu.html")
	@ResponseBody
	public Object updateCommand(HttpServletRequest request,
			HttpServletResponse res, SyMenu menu) throws IOException {
		FormSubmitRS rs = new FormSubmitRS();
		if (menu.getName() == null || menu.getName().equalsIgnoreCase("")) {
			rs.setSuccess(false);
		}
		// String pkMenus = request.getParameter("pkMenus");
		// System.out.println(pkMenus);
		boolean falg = menuService.updateMenu(menu);
		if (falg) {
			rs.setSuccess(true);
		}
		return rs;
	}

	@RequestMapping(value = "/getAllMenus.html")
	@ResponseBody
	public void getMenus(HttpServletRequest req, HttpServletResponse res)
			throws Exception {
		Util util = new Util();

		res.setContentType("text/xml; charset=UTF-8");

		Document xml = util.transMenuToXml(menuService.getAllMenus());
		PrintWriter out = res.getWriter();

		out.println(xml.asXML());
		// return authorityDao.getAllMenus();
	}

	@RequestMapping(value = "/getAuthMenus.html")
	@ResponseBody
	public void getAuthMenus(HttpServletRequest req, HttpServletResponse res)
			throws IOException {
		LoginCommand loginUser = (LoginCommand) req.getSession().getAttribute(
				"loginUser");
		PrintWriter out = res.getWriter();
		if (loginUser != null) {
			List<SyMenu> menuList = null;
			List<SyUser> users = userService.getUsers("userid='"
					+ loginUser.getUserName() + "'");
			SyUser user = users.get(0);
			List<RUserAuth> authList = authorityService.getAuthsByUser(user
					.getPkUser());
			String pkAuths = "";
			for (RUserAuth rua : authList) {
				pkAuths = pkAuths + rua.getPkAuthority() + ",";
			}
			if (!pkAuths.equalsIgnoreCase("")) {
				pkAuths = pkAuths.substring(0, pkAuths.length() - 1);
				List<RAuthMenu> rams = menuService.getMenusByAuth(pkAuths);
				String pkMenus = "";
				for (RAuthMenu ram : rams) {
					if (menuService.getMenu(ram.getPkMenu()) != null) {
						pkMenus = pkMenus + "'" + ram.getPkMenu() + "',";
					}
				}
				if (!pkMenus.equalsIgnoreCase("")) {
					pkMenus = "pkMenu in ("
							+ pkMenus.substring(0, pkMenus.length() - 1)
							+ ") order by level";
				}
				menuList = menuService.getMenus(pkMenus);
			}
			Util util = new Util();

			res.setContentType("text/xml; charset=UTF-8");

			if (menuList == null) {
				req.getSession().removeAttribute("loginUser");
				out.println("");
			} else {
				Document xml = util.transMenuToXml(menuList);
				out.println(xml.asXML());
			}

		}
		out.println("");
	}

}
