package com.itg.maindata.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itg.maindata.service.PsnService;
import com.itg.maindata.web.jsonvo.JsonPsns;

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

}
