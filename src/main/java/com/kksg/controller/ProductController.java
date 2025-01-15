package com.kksg.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kksg.contants.Status;
import com.kksg.dtos.ProductRequestDTO;
import com.kksg.dtos.ProductResponseDTO;
import com.kksg.entity.Category;
import com.kksg.entity.Product;
import com.kksg.entity.ProductOption;
import com.kksg.service.ProductService;
import com.kksg.service.impl.ProductServiceImpl;
import com.kksg.util.ApiResponse;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/product")
public class ProductController extends BaseController<Product, ProductResponseDTO, ProductResponseDTO, ProductRequestDTO> {

	private final ProductService productService;
	private ModelMapper modelMapper;

	public ProductController(ProductServiceImpl productService, ModelMapper modelMapper) {
		super(productService); // Pass CategoryServiceImpl to BaseController
		this.productService = productService; // Initialize categoryService field
		this.modelMapper = modelMapper;
	}

	
	@Override
	protected Product mapToEntity(ProductRequestDTO dto) {
	    Product product = modelMapper.map(dto, Product.class);

	    // Map Category
	    if (dto.getCategoryId() != null) {
	        Category category = new Category();
	        category.setId(dto.getCategoryId());
	        product.setCategory(category);
	    }

	    if (dto.getOptions() != null) {
	        List<ProductOption> options = dto.getOptions().stream().map(optionDTO -> {
	            ProductOption option = new ProductOption();
	            return option;
	        }).toList();
	        product.setOptions(options);
	    }


	    return product;
	}


	@Override
	protected ProductResponseDTO mapToResponse(Product entity) {
		return modelMapper.map(entity, ProductResponseDTO.class);
	}

	@Override
	protected ProductResponseDTO mapToListResponse(Product entity) {
		return modelMapper.map(entity, ProductResponseDTO.class);
	}


	//add one product
	@PostMapping("/add")
	public ResponseEntity<ApiResponse<ProductResponseDTO>> create(@Valid @RequestBody ProductRequestDTO request) {
		ProductResponseDTO saveProduct = productService.save(request);	
		return ResponseEntity.ok(new ApiResponse<>(Status.SUCCESS, null, "Saved successfully", saveProduct));
	}
	
}