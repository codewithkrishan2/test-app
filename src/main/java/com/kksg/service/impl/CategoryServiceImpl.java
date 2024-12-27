package com.kksg.service.impl;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.kksg.entity.Category;
import com.kksg.repo.CategoryRepo;
import com.kksg.service.BaseService;
import com.kksg.service.CategoryService;

@Service
public class CategoryServiceImpl extends BaseService<Category, Long> implements CategoryService {

	private CategoryRepo categoryRepository;
	
	public CategoryServiceImpl(CategoryRepo categoryRepository) {
        super(categoryRepository, categoryRepository);
        this.categoryRepository = categoryRepository;
    }

	@Override
	protected Specification<Category> prepareFilter() {
		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isDeleted"), false);
	}

	
}
