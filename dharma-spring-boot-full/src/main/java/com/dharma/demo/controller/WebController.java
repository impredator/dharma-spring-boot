package com.dharma.demo.controller;

import com.dharma.demo.dao.JpaProductDao;
import com.dharma.demo.model.Product;
import com.dharma.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/web")
public class WebController {

    @Autowired
    private ProductService productService;

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

    @RequestMapping("/product")
    public String product(ModelMap map) {
        List<Product> products = productService.getProducts();
        map.put("products", products);
        return "product";
    }

}
