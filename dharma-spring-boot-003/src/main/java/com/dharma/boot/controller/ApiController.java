package com.dharma.boot.controller;

import com.dharma.boot.bean.Product;
import com.dharma.boot.dao.ProductDao;
import com.dharma.boot.dao.impl.ProductDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping("/api")
public class ApiController {

    @Autowired
    ProductDaoImpl productDao;

	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/hello", method = RequestMethod.POST)
	public HashMap<String, Object> get(@RequestParam String name) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("action", "hello");
		map.put("name", name);
		return map;
	}

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
	public Product get(@PathVariable Integer id) {
        return productDao.selectById(id);
    }

	@RequestMapping(value = "/get/name/{name}", method = RequestMethod.GET)
	public Product getByName(@PathVariable String name) {
        return productDao.selectByName(name);
    }

	@RequestMapping(value = "/update/{id}/{name}/{price}", method = RequestMethod.PUT)
	public int update(@PathVariable Integer id, @PathVariable String name, @PathVariable Double price) {
	    Product product = new Product(id, name, price);
        return productDao.updateById(product);
    }

	@RequestMapping(value = "/add/{name}/{price}", method = RequestMethod.POST)
	public int update(@PathVariable String name, @PathVariable Double price) {
	    Product product = new Product(name, price);
        return productDao.insert(product);
    }

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public int update(@PathVariable Integer id) {
        return productDao.deleteById(id);
    }

}
