package com.kksg.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.kksg.entity.OptionValue;

public interface OptionValueRepo  extends JpaRepository<OptionValue, Long>, JpaSpecificationExecutor<OptionValue>{

	Optional<OptionValue> findByIdAndIsDeletedFalse(Long id);

	Optional<OptionValue> findByOptionIdAndIsDeletedFalse(Long id);	
}