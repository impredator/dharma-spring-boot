package com.dharma.redis.service;

import com.dharma.redis.bean.EProduct;
import com.dharma.redis.component.RedisComponent;
import com.dharma.redis.dao.EProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EProductService {

    @Autowired
    private RedisComponent redisComponent;

    @Autowired
    private EProductDao eProductDao;

    @Transactional
    public String createProduct(String name, Double price) {
        // jpa
        EProduct eProduct = new EProduct();
        eProduct.setName("Motorola");
        eProduct.setPrice(1.02);
        eProductDao.save(eProduct);

        return "success";
    }

    public List<EProduct> getProducts() {
        return eProductDao.findAll();
    }

}
