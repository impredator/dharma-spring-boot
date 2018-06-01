package com.dharma.demo.controller;

import com.dharma.demo.model.Admin;
import com.dharma.demo.model.Product;
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

    @Value(value = "What' up")
    private String title;

    @Value(value = "${dharma.project}")
    private String project;

    @Value(value = "${dharma.buildNumber}")
    private Integer buildNumber;

    @Value(value = "${dharma.desc}")
    private String desc;

    @Value(value = "${dharma.team}")
    private String[] team;

    @Value(value = "${dharma.unknown:default}")
    private String unknown;

    @Value(value = "#{admin.name}")
    private String admin;

    @Value(value = "${dharma.dburl}")
    private String dburl;

    @Bean
    public Admin admin() {
        return new Admin("ashton");
    }

    @RequestMapping
    public String index() {
        logger.debug("debug: dharma index log test");
        logger.info("info: dharma index log test");
        return title;
    }

    @RequestMapping(value = "/system")
    public HashMap<String, Object> get(@RequestParam String name) {
        HashMap<String, Object> sys = new HashMap<>();
        sys.put("slogon", "dharma mall kill taobao");
        sys.put("name", name);
        sys.put("title", title);
        sys.put("project", project);
        sys.put("build", buildNumber);
        sys.put("description", desc);
        sys.put("admin", admin);
        sys.put("team", team);
        sys.put("unknown", unknown);
        sys.put("dburl", dburl);
        return sys;
    }

    @RequestMapping(value = "/get/{id}/{name}/{price}")
    public Product get(@PathVariable Integer id, @PathVariable String name, @PathVariable Double price) {
        return new Product(id, name, price);
    }

}
