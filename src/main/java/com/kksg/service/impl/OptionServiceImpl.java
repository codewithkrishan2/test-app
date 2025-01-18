package com.kksg.service.impl;

import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.kksg.entity.Option;
import com.kksg.exception.ApiException;
import com.kksg.repo.OptionRepo;
import com.kksg.service.BaseService;
import com.kksg.service.OptionService;

@Service
public class OptionServiceImpl extends BaseService<Option, Long> implements OptionService {

	private OptionRepo optionRepository;
	
	public OptionServiceImpl(OptionRepo optionRepository) {
        super(optionRepository, optionRepository);
        this.optionRepository = optionRepository;
    }

	@Override
	protected Specification<Option> prepareFilter() {
		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("isDeleted"), false);
	}

	@Override
	protected Option preProcessBeforeSave(Option entity) {
		
		//check if the entity is already present by name in db
		Optional<Option> existingOption = optionRepository.findByNameAndIsDeletedFalse(entity.getName());
		if(existingOption.isPresent()) {
			throw new ApiException("Option already exists");
		}		
		return entity;
	}
}
