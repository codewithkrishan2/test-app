package com.kksg.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.kksg.entity.Category;
import com.kksg.exception.ApiException;
import com.kksg.repo.CategoryRepo;
import com.kksg.service.BaseService;
import com.kksg.service.CategoryService;

import jakarta.transaction.Transactional;

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
	
	@Transactional
	@Override
	protected List<Category> postProcessAfterGetData(List<Category> categories) {
	    logger.info("Post processing categories");

	    // Step 1: Create a map of categories for quick lookup by ID
	    Map<Long, Category> categoryMap = categories.stream()
	            .collect(Collectors.toMap(Category::getId, category -> category));

	    // Step 2: Create the hierarchical structure
	    List<Category> rootCategories = new ArrayList<>();
	    for (Category category : categories) {
	        if (category.getParentCategory() == null) {
	            // No parent, add to root categories
	            rootCategories.add(category);
	        } else {
	            // Find parent and add this category as a subcategory
	            Category parent = categoryMap.get(category.getParentCategory().getId());
	            if (parent != null) {
	                parent.getSubCategories().add(category);
	            }
	        }
	    }

	    // Return only root categories (highest-level nodes in the hierarchy)
	    return rootCategories;
	}

	

	@Transactional
	@Override
	protected Category postProcessAfterGetDataById(Category category) {
	    logger.info("Post processing category by ID");

	    // Map subcategories explicitly
	    if (category.getSubCategories() != null && !category.getSubCategories().isEmpty()) {
	        List<Category> resolvedSubCategories = category.getSubCategories().stream()
	                .map(subCategory -> resolveHierarchy(subCategory)) // Resolve the hierarchy recursively
	                .toList();
	        category.setSubCategories(resolvedSubCategories);
	    }

	    return category;
	}

	// Helper method to resolve category hierarchy recursively
	private Category resolveHierarchy(Category category) {
	    if (category.getSubCategories() != null && !category.getSubCategories().isEmpty()) {
	        category.setSubCategories(
	            category.getSubCategories().stream()
	                .map(this::resolveHierarchy) // Recursive call
	                .toList()
	        );
	    }
	    return category;
	}

	
	@Override
	public Category getById(Long id) {
		return categoryRepository.findByIdAndIsDeletedFalse(id)
				.orElseThrow(() -> new ApiException("Category with not found"));
	}
	
}
