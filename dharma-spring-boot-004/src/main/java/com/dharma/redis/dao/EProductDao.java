package com.dharma.redis.dao;

import com.dharma.redis.bean.EProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EProductDao extends JpaRepository<EProduct, Integer>{

	EProduct findByName(String string);

	EProduct findByNameAndPrice(String string, Double price);

	Page<EProduct> findByName(String string, Pageable pageable);
}
