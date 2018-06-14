package com.dharma.spring.data.redis.repo;

import com.dharma.spring.data.redis.model.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends CrudRepository<Product, Integer> {}
