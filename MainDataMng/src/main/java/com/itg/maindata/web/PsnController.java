package com.itg.maindata.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.itg.maindata.domain.MdPsn;
import com.itg.maindata.domain.SyAuthority;
import com.itg.maindata.service.PsnService;
import com.itg.maindata.web.jsonvo.JsonPsns;
import com.itg.maindata.web.vo.FormSubmitRS;

@Controller
public class PsnController {
	@Autowired
	PsnService psnService;

	@RequestMapping(value = "/getPsns.html")
	public String getAllPsns() {
		return "md/psn/psnList";
	}

	@RequestMapping(value = "/getPsnList.html")
	@ResponseBody
	public Object getAuthList() {
		JsonPsns psns = new JsonPsns();
		psns.setPsns(psnService.getAllPsns());
		return psns;
	}

	@RequestMapping(value = "/getPsn/{id}")
	public ModelAndView getPsn(@PathVariable("id") String id) {
		ModelAndView mav = new ModelAndView("md/psn/psn");

		MdPsn psn = psnService.getPsn(id);

		if (psn == null) {
			psn = new MdPsn();
		}

		mav.addObject("psn", psn);

		return mav;
	}

	@RequestMapping(value = "/updatePsn.html")
	@ResponseBody
	public Object updateCommand(HttpServletRequest request,
			HttpServletResponse res, MdPsn psn) throws IOException {
		FormSubmitRS rs = new FormSubmitRS();
		if (psn.getPsnname() == null || psn.getPsnname().equalsIgnoreCase("")) {
			rs.setSuccess(false);
		}
		// String pkMenus = request.getParameter("pkMenus");
		// System.out.println(pkMenus);
		boolean falg = psnService.updatePsn(psn);
		if (falg) {
			rs.setSuccess(true);
		}
		return rs;
	}
}
