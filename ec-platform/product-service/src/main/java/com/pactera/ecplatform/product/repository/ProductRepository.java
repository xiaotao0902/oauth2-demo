package com.pactera.ecplatform.product.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pactera.ecplatform.product.domain.Product;


@Repository("productRepository")
public interface ProductRepository extends CrudRepository<Product, Long> {

	List<Product> findByname(String name);
}
