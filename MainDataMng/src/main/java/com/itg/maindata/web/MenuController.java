package com.itg.maindata.web;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itg.maindata.dao.AuthorityDao;
import com.itg.maindata.service.AuthorityService;
import com.itg.maindata.service.MenuService;
import com.itg.maindata.service.UserService;
import com.itg.maindata.service.Util;

@Controller
public class MenuController {
	@Autowired
	private UserService userService;

	@Autowired
	private AuthorityService authorityService;

	@Autowired
	private MenuService menuService;

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
}
