package com.kksg.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.kksg.entity.Category;

public interface CategoryRepo extends JpaRepository<Category, Long>, JpaSpecificationExecutor<Category>{

	Optional<Category> findByIdAndIsDeletedFalse(Long id);
    
    Optional<Category> findByNameAndIsDeletedFalseAndIdNot(String email, Long id);
	
}
