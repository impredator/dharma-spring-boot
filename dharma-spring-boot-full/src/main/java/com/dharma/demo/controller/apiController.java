package com.dharma.demo.controller;

import com.dharma.demo.component.RedisComponent;
import com.dharma.demo.dao.ProductDaoImple;
import com.dharma.demo.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class apiController {

    private static final Logger logger =
            LoggerFactory.getLogger(apiController.class);

    @Autowired
    private ProductDaoImple productDaoImple;

    @Autowired
    private RedisComponent redisCache;

    @RequestMapping(value = "/get/id/{id}", method = RequestMethod.GET)
    public Product getById(@PathVariable Integer id) {
        return productDaoImple.getById(id);
    }

    @RequestMapping(value = "/get/name/{name}", method = RequestMethod.GET)
    public Product getByName(@PathVariable String name) {
        return productDaoImple.getByName(name);
    }

    @RequestMapping(value = "/add/name/{name}/price/{price}", method = RequestMethod.POST)
    public int add(@PathVariable String name, @PathVariable Double price) {
        return productDaoImple.insert(new Product(name, price));
    }

//    @CachePut(value = "product", key = "#id")
    @RequestMapping(value = "/update/id/{id}/name/{name}/price/{price}", method = RequestMethod.PUT)
    public int update(@PathVariable Integer id, @PathVariable String name, @PathVariable Double price) {
        return productDaoImple.updateById(new Product(id, name, price));
    }

    @CacheEvict(value = "product", allEntries = true)
    @RequestMapping(value = "/delete/id/{id}", method = RequestMethod.DELETE)
    public int delete(@PathVariable Integer id) {
        logger.info("delete product by id = " + id);
        return productDaoImple.deleteById(id);
    }

    @RequestMapping(value = "/get/{id}/name", method = RequestMethod.GET)
    public String getProductName(@PathVariable Integer id) {
        String cachedName = redisCache.get(id.toString());
        if(null == cachedName) {
            Product product = productDaoImple.getById(id);
            String name = product.getName();
            redisCache.set(id.toString(), name);

            logger.info("get product name by id = " + id);
            return name;
        }
        return cachedName;
    }

}
