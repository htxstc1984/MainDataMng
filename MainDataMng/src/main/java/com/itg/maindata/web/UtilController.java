package com.itg.maindata.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.itg.maindata.service.Util;
import com.itg.maindata.service.UtilService;
import com.itg.maindata.web.vo.FormSubmitRS;

@Controller
public class UtilController {

	@Autowired
	UtilService utilService;

	@RequestMapping(value = "/getColums.html")
	@ResponseBody
	public Object getColumsByClass(@RequestParam String className,
			@RequestParam String pk) throws Exception {
		Object instance = null;
		Util util = new Util();
		if (pk != null && !pk.equalsIgnoreCase("")) {
			instance = utilService.getVOByPk(className, pk);
		}
		return util.getColumsByClass(className, instance);
	}
	

}
