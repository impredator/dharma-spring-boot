package com.dharma.boot.controller;

import com.dharma.boot.bean.EProduct;
import com.dharma.boot.bean.Product;
import com.dharma.boot.dao.EProductDao;
import com.dharma.boot.dao.impl.ProductDaoImpl;
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
    EProductDao productDao;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public List<EProduct> get() {
        return productDao.findAll();
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public EProduct get(@PathVariable Integer id) {
        return productDao.findById(id).orElse(new EProduct("null", 0.0));
    }

    @RequestMapping(value = "/get/name/{name}", method = RequestMethod.GET)
    public EProduct getByName(@PathVariable String name) {
        return productDao.findByName(name);
    }

    @RequestMapping(value = "/get/name/{name}/price/{price}", method = RequestMethod.GET)
    public EProduct getByNameAndPrice(@PathVariable String name, @PathVariable Double price) {
        return productDao.findByNameAndPrice(name, price);
    }

    @RequestMapping(value = "/update/{id}/{name}/{price}", method = RequestMethod.PUT)
    public EProduct update(@PathVariable Integer id, @PathVariable String name, @PathVariable Double price) {
        EProduct product = new EProduct(id, name, price);
        return productDao.save(product);
    }

    @RequestMapping(value = "/add/{name}/{price}", method = RequestMethod.POST)
    public EProduct update(@PathVariable String name, @PathVariable Double price) {
        EProduct product = new EProduct(name, price);
        return productDao.save(product);
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
    public void update(@PathVariable Integer id) {
        productDao.deleteById(id);
    }

}
