package com.kksg.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.kksg.dtos.ProductRequestDTO;
import com.kksg.dtos.ProductResponseDTO;
import com.kksg.entity.Category;
import com.kksg.entity.Product;
import com.kksg.repo.CategoryRepo;
import com.kksg.repo.ProductOptionRepo;
import com.kksg.repo.ProductRepo;
import com.kksg.repo.ProductVarientRepo;
import com.kksg.service.BaseService;
import com.kksg.service.ProductService;

@Service
public class ProductServiceImpl extends BaseService<Product, Long> implements ProductService {

	private ProductRepo productRepository;
	private CategoryRepo categoryRepository;
	private ProductOptionRepo optionRepository;
	
	public ProductServiceImpl(ProductRepo productRepository, CategoryRepo categoryRepository, ProductOptionRepo optionRepository) {
        super(productRepository, productRepository);
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.optionRepository = optionRepository;
    }

	@Override
	protected Specification<Product> prepareFilter() {
		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isDeleted"), false);
	}
	
	@Override
	protected Product preProcessBeforeSave(Product product) {
	    // Ensure Category is resolved
		if (product.getCategory() != null && product.getCategory().getId() != null) {
	        Category category = categoryRepository.findById(product.getCategory().getId())
	            .orElseThrow(() -> new RuntimeException("Category not found with ID: " + product.getCategory().getId()));
	        product.setCategory(category);
	    }


	    // Process Product Options
	    if (product.getOptions() != null) {
	        product.getOptions().forEach(option -> {
	            option.setProduct(product);
	            if (option.getValues() != null) {
	                option.getValues().forEach(value -> value.setOption(option));
	            }
	        });
	    }

	    // Process Product Variants
	    if (product.getVariants() != null) {
	        product.getVariants().forEach(variant -> {
	            variant.setProduct(product);
	            if (variant.getOptionValues() != null) {
	                variant.getOptionValues().forEach(value -> {
	                    if (value.getOption() != null) {
	                        value.setOption(optionRepository.findByNameAndProductAndIsDeletedFalse(value.getOption().getName(), product)
	                            .orElseThrow(() -> new RuntimeException("Option not found: " + value.getOption().getName())));
	                    }
	                });
	            }
	        });
	    }

	    return super.preProcessBeforeSave(product);
	}

	@Override
	public ProductResponseDTO save(ProductRequestDTO request) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'save'");
	}

	
}
