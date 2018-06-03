package com.dharma.demo.service;

import com.dharma.demo.dao.JpaProductDao;
import com.dharma.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    @Autowired
    private JpaProductDao productDao;

    public List<Product> getProducts() {
        return productDao.findAll();
    }

    public Product getProductById(Integer id) {
        return productDao.findById(id).orElse(new Product(-1, "not exist", 0.0));
    }

    public Product getProductByNameAndPrice(String name, Double price) {
        return productDao.findByNameAndPrice(name, price);
    }
}
