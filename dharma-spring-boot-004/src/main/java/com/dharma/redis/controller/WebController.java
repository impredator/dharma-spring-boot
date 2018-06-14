package com.dharma.redis.controller;

import com.dharma.redis.bean.EProduct;
import com.dharma.redis.service.EProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/web")
public class WebController {

	private static final Logger logger =  LoggerFactory.getLogger(WebController.class);

	@Autowired
	EProductService productService;

	@RequestMapping
	public String index(ModelMap map){
		logger.info("web controller - freemarker");
		map.put("title", "FIRST BLOOD");
		return "index";
	}

	@RequestMapping("/error")
	public String error(ModelMap map){
		throw new RuntimeException("testing exception");
	}

	@RequestMapping("/product")
	public String product(ModelMap map){
		List<EProduct> products = productService.getProducts();
		map.put("products", products);
		return "product";
	}

}
