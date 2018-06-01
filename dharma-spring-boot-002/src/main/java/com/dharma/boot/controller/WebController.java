package com.dharma.boot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web")
public class WebController {

	private static final Logger logger =  LoggerFactory.getLogger(WebController.class);

	@RequestMapping
	public String index(ModelMap map){
		logger.info("web controller - freemarker");
		map.put("title", "FIRST BLOOD");
		return "index";
	}

	@RequestMapping(value = "upload")
	public String upload(ModelMap map){
		logger.info("web controller - file upload - freemarker");
		map.put("title", "FILE UPLOAD");
		return "upload";
	}

	@RequestMapping("/thymeleaf")
	public String thymeleaf(ModelMap map){
		logger.info("web controller - thymeleaf");
		map.put("title", "SECOND BLOOD");
		return "index2";
	}

	@RequestMapping("/error")
	public String error(ModelMap map){
		throw new RuntimeException("testing exception");
	}


}
