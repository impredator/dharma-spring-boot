package com.dharma.demo.controller;

import com.dharma.demo.dao.JpaProductDao;
import com.dharma.demo.model.Product;
import com.dharma.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/get/id/{id}", method = RequestMethod.GET)
    public Product get(@PathVariable Integer id) {
        return productService.getProductById(id);
    }

    @RequestMapping(value = "/get/name/{name}/price/{price}", method = RequestMethod.GET)
    public Product get(@PathVariable String name, @PathVariable Double price) {
        return productService.getProductByNameAndPrice(name, price);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public List<Product> get() {
        return productService.getProducts();
    }
}
