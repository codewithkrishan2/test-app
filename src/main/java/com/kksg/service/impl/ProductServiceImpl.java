package com.kksg.service.impl;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.kksg.dtos.ProductRequestDTO;
import com.kksg.entity.Product;
import com.kksg.repo.ProductRepo;
import com.kksg.service.BaseService;
import com.kksg.service.ProductService;

@Service
public class ProductServiceImpl extends BaseService<Product, Long> implements ProductService {

	private ProductRepo productRepository;
	
	public ProductServiceImpl(ProductRepo productRepository) {
        super(productRepository, productRepository);
        this.productRepository = productRepository;
    }

	@Override
	protected Specification<Product> prepareFilter() {
		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isDeleted"), false);
	}
	
	@Override
	protected Product preProcessBeforeSave(Product product) {
	    // Example: Check if product has at least one size
	    if (product.getSizes() == null || product.getSizes().isEmpty()) {
	        throw new IllegalArgumentException("A product must have at least one size.");
	    }

	    // Example: Check if product has at least one image
	    if (product.getImages() == null || product.getImages().isEmpty()) {
	        throw new IllegalArgumentException("A product must have at least one image.");
	    }
	    
	    return super.preProcessBeforeSave(product);
	}

	
	
}
