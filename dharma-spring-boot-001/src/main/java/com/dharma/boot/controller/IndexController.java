package com.dharma.boot.controller;

import com.dharma.boot.bean.Admin;
import com.dharma.boot.bean.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequestMapping(value = "/index")
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @Value(value = "what's up")
    private String title;

	@Value(value = "${dharma.randomInt}")
	private int buildNumber;

	@Value(value = "${dharma.project}")
	private String project;

    @Value(value = "${dharma.randomString}")
    private String desc;

    @Value(value = "${dharma.unknown:default}")
    private String unknown;

    @Value(value = "${dharma.team}")
    private String[] team;

    @Value(value = "#{admin.name}")
    private String admin;

	@RequestMapping
	public String index() {
        logger.debug("this is a log test, debug");
        logger.info("this is a log test, info");
        return "What's up";
	}

    @Bean
    public Admin admin() {
        return new Admin("ashton");
    }

    @RequestMapping(value = "/get")
	public HashMap<String, Object> get(@RequestParam String name) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("title", title);
		map.put("name", name);
		map.put("project", project);
		map.put("build", buildNumber);
		map.put("description", desc);
		map.put("trivial", unknown);
		map.put("team", team);
		map.put("admin", admin);
		return map;
	}

	@RequestMapping(value = "/get/{id}/{name}/{price}")
	public Product getProduct(@PathVariable int id, @PathVariable String name, @PathVariable Double price) {
		Product product = new Product();
		product.setId(id);
		product.setName(name);
		product.setPrice(price);
		return product;
	}

}
