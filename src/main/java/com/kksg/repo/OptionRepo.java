package com.kksg.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.kksg.entity.Option;

public interface OptionRepo extends JpaRepository<Option, Long>, JpaSpecificationExecutor<Option>{

	Optional<Option> findByIdAndIsDeletedFalse(Long id);

	Optional<Option> findByNameAndIsDeletedFalse(String name);	
}
