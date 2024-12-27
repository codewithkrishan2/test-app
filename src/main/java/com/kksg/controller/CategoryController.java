package com.kksg.controller;

import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kksg.dtos.CategoryRequestDTO;
import com.kksg.dtos.CategoryResponseDTO;
import com.kksg.entity.Category;
import com.kksg.service.CategoryService;
import com.kksg.service.impl.CategoryServiceImpl;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController extends BaseController<Category, CategoryResponseDTO, CategoryRequestDTO> {

	private final CategoryService categoryService;
	private ModelMapper modelMapper;

	public CategoryController(CategoryServiceImpl categoryService, ModelMapper modelMapper) {
		super(categoryService); // Pass CategoryServiceImpl to BaseController
		this.categoryService = categoryService; // Initialize categoryService field
		this.modelMapper = modelMapper;
	}

	@Override
	protected Category mapToEntity(CategoryRequestDTO dto) {
		return modelMapper.map(dto, Category.class);
	}

	@Override
	protected CategoryResponseDTO mapToResponse(Category entity) {
		return modelMapper.map(entity, CategoryResponseDTO.class);
	}

}