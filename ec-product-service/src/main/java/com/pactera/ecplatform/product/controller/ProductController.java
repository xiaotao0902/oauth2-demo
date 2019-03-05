package com.pactera.ecplatform.product.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pactera.ecplatform.product.domain.Product;
import com.pactera.ecplatform.product.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/searchByName", method = RequestMethod.GET)
	public List<Product> searchByName(@RequestParam(value = "name", required = false) String name) {
		List<Product> products = new ArrayList<Product>();

		if (name == null || "".equals(name)) {
			products = productService.searchAll();
		} else {
			products = productService.searchByName(name);
		}
		return products;
	}

}
