package com.dharma.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/web")
public class WebController {

    @RequestMapping
    public String index(ModelMap map) {
        map.put("title", "FIRST BLOOD");
        return "index";
    }

    @RequestMapping(value = "/upload")
    public String upload() {
        return "upload";
    }

    @RequestMapping("/error")
    public String error() {
        throw new RuntimeException("test dharma exception");
    }
}
