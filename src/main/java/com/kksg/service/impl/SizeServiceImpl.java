package com.kksg.service.impl;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.kksg.entity.Size;
import com.kksg.repo.SizeRepo;
import com.kksg.service.BaseService;
import com.kksg.service.SizeService;

@Service
public class SizeServiceImpl extends BaseService<Size, Long> implements SizeService {

	private SizeRepo sizeRepository;
	
	public SizeServiceImpl(SizeRepo sizeRepository) {
        super(sizeRepository, sizeRepository);
        this.sizeRepository = sizeRepository;
    }

	@Override
	protected Specification<Size> prepareFilter() {
		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isDeleted"), false);
	}
	
}
