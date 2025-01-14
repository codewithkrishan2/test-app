package com.kksg.service.impl;

import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.kksg.entity.Option;
import com.kksg.entity.OptionValue;
import com.kksg.exception.ApiException;
import com.kksg.repo.OptionValueRepo;
import com.kksg.service.BaseService;
import com.kksg.service.OptionValueService;

@Service
public class OptionValueServiceImpl extends BaseService<OptionValue, Long> implements OptionValueService {

	private OptionValueRepo optionValueRepository;
	
	public OptionValueServiceImpl(OptionValueRepo optionValueRepository) {
        super(optionValueRepository, optionValueRepository);
        this.optionValueRepository = optionValueRepository;
    }

	@Override
	protected Specification<OptionValue> prepareFilter() {
		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isDeleted"), false);
	}
	
//	@Override
//	protected OptionValue preProcessBeforeSave(OptionValue entity) {
//
//	}
}
