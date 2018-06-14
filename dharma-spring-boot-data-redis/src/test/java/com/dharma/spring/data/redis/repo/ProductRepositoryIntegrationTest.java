package com.dharma.spring.data.redis.repo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import com.dharma.spring.data.redis.model.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dharma.spring.data.redis.config.RedisConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RedisConfig.class)
public class ProductRepositoryIntegrationTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void whenSavingProduct_thenAvailableOnRetrieval() {
        final Product product = new Product(1, "redis", 1000.0);
        productRepository.save(product);
        final Product retrievedProduct = productRepository.findById(product.getId()).get();
        assertEquals(product.getId(), retrievedProduct.getId());
    }

    @Test
    public void whenUpdatingProduct_thenAvailableOnRetrieval() {
        final Product product = new Product(1, "redis", 1000.0);
        productRepository.save(product);
        product.setName("jedis");
        productRepository.save(product);
        final Product retrievedProduct = productRepository.findById(product.getId()).get();
        assertEquals(product.getName(), retrievedProduct.getName());
    }

    @Test
    public void whenSavingProducts_thenAllShouldAvailableOnRetrieval() throws Exception {
        final Product redis = new Product(1, "redis", 0.0);
        final Product mysql = new Product(2, "mysql", 5000.0);
        productRepository.save(redis);
        productRepository.save(mysql);
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        assertEquals(products.size(), 2);
    }

    @Test
    public void whenDeletingProduct_thenNotAvailableOnRetrieval() throws Exception {
        final Product product = new Product(1, "redis", 0.0);
        productRepository.save(product);
        productRepository.deleteById(product.getId());
        final Product retrievedProduct = productRepository.findById(product.getId()).orElse(null);
        assertNull(retrievedProduct);
    }
}