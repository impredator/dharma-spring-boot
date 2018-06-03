package com.dharma.demo.dao;

import com.dharma.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaProductDao extends JpaRepository<Product, Integer> {
    Product findByNameAndPrice(String name, Double price);
}
