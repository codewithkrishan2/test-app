package com.kksg.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.kksg.dtos.CategoryListResponseDTO;
import com.kksg.entity.Category;
import com.kksg.exception.ApiException;
import com.kksg.repo.CategoryRepo;
import com.kksg.service.BaseService;
import com.kksg.service.CategoryService;

@Service
public class CategoryServiceImpl extends BaseService<Category, Long> implements CategoryService {

	private static final Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);
	
	private CategoryRepo categoryRepository;
	
	public CategoryServiceImpl(CategoryRepo categoryRepository) {
        super(categoryRepository, categoryRepository);
        this.categoryRepository = categoryRepository;
    }

	@Override
	protected Specification<Category> prepareFilter() {
		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isDeleted"), false);
	}
	
	@Override
	protected Category preProcessBeforeSave(Category category) {		
		Optional<Category> existingCategory = categoryRepository.findByNameAndIsDeletedFalse(category.getName());
		if (existingCategory.isPresent()) {
			throw new ApiException("Category with name: " + category.getName() + " already exists");
		}
		return category;
	}
	
	
	//overrite postProcessAfterGetData method
	@Override
	protected List<Category> postProcessAfterGetData(List<Category> categories) {
		logger.info("Post processing categories");
		
				
		return super.postProcessAfterGetData(categories.stream()
		        .map(category -> {
		            // Map subcategories explicitly to prevent infinite recursion
		            category.setSubCategories(
		                category.getSubCategories().stream()
		                    .map(subCategory -> {
		                        // Create a shallow copy of subCategory without further nested subcategories
		                        Category shallowCopy = new Category();
		                        shallowCopy.setId(subCategory.getId());
		                        shallowCopy.setName(subCategory.getName());
		                        shallowCopy.setDescription(subCategory.getDescription());
		                        shallowCopy.setParentCategory(null); // Prevent circular reference
		                        shallowCopy.setSubCategories(Collections.emptyList()); // Avoid further nesting
		                        return shallowCopy;
		                    })
		                    .collect(Collectors.toList())
		            );
		            return category;
		        })
		        .collect(Collectors.toList()));
	}
	

	
	@Override
	protected Category postProcessAfterGetDataById(Category category) {
		logger.info("Post processing categories");
		
		// Map subcategories explicitly to prevent infinite recursion
	    category.setSubCategories(
	        category.getSubCategories().stream()
	            .map(subCategory -> {
	                // Create a shallow copy of subCategory without further nested subcategories
	                Category shallowCopy = new Category();
	                shallowCopy.setId(subCategory.getId());
	                shallowCopy.setName(subCategory.getName());
	                shallowCopy.setDescription(subCategory.getDescription());
	                shallowCopy.setParentCategory(null); // Prevent circular reference
	                shallowCopy.setSubCategories(Collections.emptyList()); // Avoid further nesting
	                return shallowCopy;
	            })
	            .collect(Collectors.toList())
	    );

	    return category;
		
	}
	
	@Override
	public Category getById(Long id) {
		return categoryRepository.findByIdAndIsDeletedFalse(id)
				.orElseThrow(() -> new ApiException("Category with not found"));
	}
	
}
