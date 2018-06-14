package com.dharma.redis.controller;

import com.dharma.redis.bean.EProduct;
import com.dharma.redis.component.RedisComponent;
import com.dharma.redis.dao.EProductDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private static final Logger logger =  LoggerFactory.getLogger(WebController.class);

    @Autowired
    EProductDao productDao;

    @Autowired
    RedisComponent redisCache;

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public List<EProduct> get() {
        return productDao.findAll();
    }

    @Cacheable(value = "products", key = "#id", unless = "#result.price > 5000")
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public EProduct get(@PathVariable Integer id) {
        logger.info("get product from db by id =" + id);
        return productDao.findById(id).orElse(new EProduct("null", 0.0));
    }

    @RequestMapping(value = "/get/{id}/price", method = RequestMethod.GET)
    public String getPrice(@PathVariable Integer id) {
        String cachedPrice = redisCache.get(id.toString());
        if(null == cachedPrice) {
            EProduct product = productDao.findById(id).orElse(new EProduct("null", -1.0));
            String price = product.getPrice().toString();
            redisCache.set(id.toString(), price);
            return price;
        }
        return cachedPrice;
    }

    @RequestMapping(value = "/get/name/{name}", method = RequestMethod.GET)
    public EProduct getByName(@PathVariable String name) {
        return productDao.findByName(name);
    }

    @RequestMapping(value = "/get/name/{name}/price/{price}", method = RequestMethod.GET)
    public EProduct getByNameAndPrice(@PathVariable String name, @PathVariable Double price) {
        return productDao.findByNameAndPrice(name, price);
    }

    @CachePut(value = "products", key = "#id")
//    @PutMapping("/update/{id}/{name}/{price}")
    @RequestMapping(value = "/update/{id}/{name}/{price}", method = RequestMethod.PUT)
    public EProduct update(@PathVariable Integer id, @PathVariable String name, @PathVariable Double price) {
        logger.info("update product by id =" + id);
        EProduct product = new EProduct(id, name, price);
        return productDao.save(product);
    }

    @RequestMapping(value = "/add/{name}/{price}", method = RequestMethod.POST)
    public EProduct update(@PathVariable String name, @PathVariable Double price) {
        EProduct product = new EProduct(name, price);
        return productDao.save(product);
    }

    @CacheEvict(value = "products", allEntries=true)
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
//    @DeleteMapping("/delete/{id}")
    public void update(@PathVariable Integer id) {
        logger.info("delete product by id =" + id);
        productDao.deleteById(id);
    }

}
