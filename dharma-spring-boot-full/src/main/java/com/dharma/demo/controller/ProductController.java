package com.dharma.demo.controller;

import com.dharma.demo.dao.JpaProductDao;
import com.dharma.demo.model.Product;
import com.dharma.demo.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private static final Logger logger =
            LoggerFactory.getLogger(ProductController.class);

    @Autowired
    ProductService productService;

    @Cacheable(value = "product", key = "#id", unless = "#result.price > 5000")
    @RequestMapping(value = "/get/id/{id}", method = RequestMethod.GET)
    public Product get(@PathVariable Integer id) {
        logger.info("get product by id = " + id);
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
