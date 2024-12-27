package com.kksg.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.kksg.entity.CategoryEntity;

public interface CategoryRepo extends JpaRepository<CategoryEntity, Long>, JpaSpecificationExecutor<CategoryEntity>{

	Optional<CategoryEntity> findByIdAndIsDeletedFalse(Long id);
    
    Optional<CategoryEntity> findByNameAndIsDeletedFalseAndIdNot(String email, Long id);
	
}
