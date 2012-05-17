package com.itg.maindata.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itg.maindata.service.LoginService;
import com.itg.maindata.web.vo.FormSubmitRS;
import com.itg.maindata.web.vo.LoginCommand;

@Controller
public class LoginController {

	@Autowired
	private LoginService loginService;

	@RequestMapping(value = "/login.html")
	@ResponseBody
	public Object login(HttpServletRequest req, HttpServletResponse res,
			LoginCommand loginCommand) {

		FormSubmitRS rs = new FormSubmitRS();
		boolean success = loginService.validUser(loginCommand.getUserName(),
				loginCommand.getPassword());
		if (success) {
			req.getSession().setAttribute("loginUser", loginCommand);
			rs.setSuccess(true);
		} else {
			rs.setSuccess(false);
		}

		return rs;
	}

	@RequestMapping(value = "/logout.html")
	public String logout(HttpServletRequest req, HttpServletResponse res) {

		req.getSession().removeAttribute("loginUser");

		return "login";
	}

	@RequestMapping(value = "/index.html")
	public String goLogin(HttpServletRequest req, HttpServletResponse res) {
		LoginCommand logincmd = (LoginCommand) req.getSession().getAttribute(
				"loginUser");
		if (logincmd != null) {
			return "user/testExtjs";
		}
		return "login";
	}

	@RequestMapping(value = "/index")
	public String goRoot(HttpServletRequest req, HttpServletResponse res) {
		LoginCommand logincmd = (LoginCommand) req.getSession().getAttribute(
				"loginUser");
		if (logincmd != null) {
			return "user/testExtjs";
		}
		return "login";
	}
}
