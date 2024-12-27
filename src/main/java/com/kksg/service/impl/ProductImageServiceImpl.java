package com.kksg.service.impl;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.kksg.entity.ProductImage;
import com.kksg.repo.ProductImageRepo;
import com.kksg.service.BaseService;
import com.kksg.service.ProductImageService;

@Service
public class ProductImageServiceImpl extends BaseService<ProductImage, Long> implements ProductImageService {

	private ProductImageRepo productImageRepository;

	public ProductImageServiceImpl(ProductImageRepo productImageRepository) {
		super(productImageRepository, productImageRepository);
		this.productImageRepository = productImageRepository;
	}

	@Override
	protected Specification<ProductImage> prepareFilter() {
		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isDeleted"), false);
	}

}
