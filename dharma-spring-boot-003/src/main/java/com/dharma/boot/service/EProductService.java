package com.dharma.boot.service;

import com.dharma.boot.bean.EProduct;
import com.dharma.boot.bean.Product;
import com.dharma.boot.dao.EProductDao;
import com.dharma.boot.dao.ProductDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EProductService {

	@Autowired
	private EProductDao eProductDao;

	@Autowired
	private ProductDao productDao;

	@Transactional
	public String createProduct(String name, Double price) {
		//jdbc
		Product product = new Product("Huawei Magic", 4999.9);
		productDao.insert(product);

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
