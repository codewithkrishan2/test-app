package com.kksg.service.impl;

import org.springframework.data.jpa.domain.Specification;

import com.kksg.entity.CategoryEntity;
import com.kksg.repo.CategoryRepo;
import com.kksg.service.BaseService;
import com.kksg.service.CategoryService;

public class CategoryServiceImpl extends BaseService<CategoryEntity, Long> implements CategoryService {

	private CategoryRepo categoryRepository;
	
	public CategoryServiceImpl(CategoryRepo categoryRepository) {
        super(categoryRepository, categoryRepository);
        this.categoryRepository = categoryRepository;
    }

	@Override
	protected Specification<CategoryEntity> prepareFilter() {
		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isDeleted"), false);
	}

	
}
