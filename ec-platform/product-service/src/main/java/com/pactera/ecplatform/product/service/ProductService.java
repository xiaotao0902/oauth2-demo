package com.pactera.ecplatform.product.service;

import java.util.List;

import com.pactera.ecplatform.product.domain.Product;

public interface ProductService {

	List<Product> searchByName(String name);
	
	List<Product> searchAll();

}