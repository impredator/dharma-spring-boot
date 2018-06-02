package com.dharma.boot.dao;

import com.dharma.boot.bean.Product;
import com.dharma.boot.utils.Page;

public interface ProductDao {

	int insert(Product product);

	int deleteById(int id);

	int updateById(Product product);

	Product selectById(int id);

	Product selectByName(String name);

	Page<Product> queryForPage(int i, int j, String string);

}
