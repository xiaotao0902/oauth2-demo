package com.pactera.ecplatform.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pactera.ecplatform.product.domain.Product;
import com.pactera.ecplatform.product.repository.ProductRepository;


@Service
public class ProductServiceImpl implements ProductService {


	@Autowired
	private ProductRepository repository;

	@Override
	public List<Product> searchByName(String name) {

		List<Product> products = repository.findByname(name);
		
		return products;
	}
	
	@Override
	public List<Product> searchAll() {

		List<Product> products = (List<Product>) repository.findAll();
		
		return products;
	}
}
