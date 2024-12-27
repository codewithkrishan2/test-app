package com.kksg.controller;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kksg.dtos.ProductRequestDTO;
import com.kksg.dtos.ProductResponseDTO;
import com.kksg.entity.Product;
import com.kksg.service.ProductService;
import com.kksg.service.impl.ProductServiceImpl;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController extends BaseController<Product, ProductResponseDTO, ProductRequestDTO> {

	private final ProductService productService;
	private ModelMapper modelMapper;

	public ProductController(ProductServiceImpl productService, ModelMapper modelMapper) {
		super(productService); // Pass CategoryServiceImpl to BaseController
		this.productService = productService; // Initialize categoryService field
		this.modelMapper = modelMapper;
	}

	@Override
	protected Product mapToEntity(ProductRequestDTO dto) {
		return modelMapper.map(dto, Product.class);
	}

	@Override
	protected ProductResponseDTO mapToResponse(Product entity) {
		return modelMapper.map(entity, ProductResponseDTO.class);
	}


}