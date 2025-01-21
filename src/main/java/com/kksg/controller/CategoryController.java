package com.kksg.controller;

import java.util.Collections;
import java.util.List;

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
	    CategoryResponseDTO catDto = new CategoryResponseDTO();
	    catDto.setId(entity.getId());
	    catDto.setCreated(entity.getCreated() != null ? entity.getCreated().toString() : null);
	    catDto.setModified(entity.getModified() != null ? entity.getModified().toString() : null);
	    catDto.setName(entity.getName());
	    catDto.setDescription(entity.getDescription());

	    // Map parentCategory with only essential fields
	    if (entity.getParentCategory() != null) {
	        CategoryResponseDTO parentDto = new CategoryResponseDTO();
	        parentDto.setId(entity.getParentCategory().getId());
	        parentDto.setName(entity.getParentCategory().getName());
	        parentDto.setDescription(entity.getParentCategory().getDescription());
	        catDto.setParentCategory(parentDto);
	    } else {
	        catDto.setParentCategory(null);
	    }

	    // Map subCategories recursively without circular parent references
	    if (entity.getSubCategories() != null && !entity.getSubCategories().isEmpty()) {
	        List<CategoryResponseDTO> subCategoryDtos = entity.getSubCategories().stream().map(subCategory -> {
	            CategoryResponseDTO subDto = mapToResponse(subCategory); // Recursive call
	            subDto.setParentCategory(null); // Prevent circular reference
	            return subDto;
	        }).toList();
	        catDto.setSubCategories(subCategoryDtos);
	    } else {
	        catDto.setSubCategories(Collections.emptyList());
	    }

	    return catDto;
	}




	@Override
	protected CategoryListResponseDTO mapToListResponse(Category entity) {
	    CategoryListResponseDTO map = modelMapper.map(entity, CategoryListResponseDTO.class);

	    // Safely check if the parentCategory is null before accessing getId()
	    if (entity.getParentCategory() != null) {
	        map.setParentCategoryId(entity.getParentCategory().getId());
	    } else {
	        map.setParentCategoryId(null); // or any default value if required
	    }

	    // Map subCategories with minimal details (IDs and names)
	    if (entity.getSubCategories() != null && !entity.getSubCategories().isEmpty()) {
	        map.setSubCategories(
	            entity.getSubCategories().stream()
	                .map(subCategory -> {
	                    CategoryListResponseDTO subCategoryDto = new CategoryListResponseDTO();
	                    subCategoryDto.setId(subCategory.getId());
	                    subCategoryDto.setName(subCategory.getName());
	                    return subCategoryDto; // Minimal fields for list response
	                })
	                .toList()
	        );
	    } else {
	        map.setSubCategories(Collections.emptyList()); // Return an empty list if no sub categories
	    }

	    return map;
	}


}