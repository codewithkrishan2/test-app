package com.kksg.controller;

import java.util.Collections;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kksg.dtos.CategoryListResponseDTO;
import com.kksg.dtos.CategoryRequestDTO;
import com.kksg.dtos.CategoryResponseDTO;
import com.kksg.entity.Category;
import com.kksg.service.CategoryService;
import com.kksg.service.impl.CategoryServiceImpl;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController extends BaseController<Category, CategoryResponseDTO, CategoryListResponseDTO , CategoryRequestDTO> {

	private final CategoryService categoryService;
	private ModelMapper modelMapper;

	public CategoryController(CategoryServiceImpl categoryService, ModelMapper modelMapper) {
		super(categoryService); // Pass CategoryServiceImpl to BaseController
		this.categoryService = categoryService; // Initialize categoryService field
		this.modelMapper = modelMapper;
	}

	@Override
	protected Category mapToEntity(CategoryRequestDTO dto) {
		Category map = modelMapper.map(dto, Category.class);
		map.setParentCategory(categoryService.getById(dto.getParentCategoryId()));
		return map;
	}

	@Override
	protected CategoryResponseDTO mapToResponse(Category entity) {
		return modelMapper.map(entity, CategoryResponseDTO.class);
	}

	@Override
	protected CategoryListResponseDTO mapToListResponse(Category entity) {
		CategoryListResponseDTO map = modelMapper.map(entity, CategoryListResponseDTO.class);
//		map.setParentCategoryId(entity.getParentCategory().getId());
		// Safely check if the parentCategory is null before accessing getId()
	    if (entity.getParentCategory() != null) {
	        map.setParentCategoryId(entity.getParentCategory().getId());
	    } else {
	        map.setParentCategoryId(null); // or any default value if required
	    }
	    
	    // Map the subCategories explicitly using Stream API
	    if (entity.getSubCategories() != null && !entity.getSubCategories().isEmpty()) {
	        map.setSubCategories(
	            entity.getSubCategories().stream()
	                .map(subCategory -> modelMapper.map(subCategory, CategoryListResponseDTO.class))
	                .collect(Collectors.toList())
	        );
	    } else {
	        map.setSubCategories(Collections.emptyList()); // Return an empty list if there are no subCategories
	    }
	    
		return map;
	}

}