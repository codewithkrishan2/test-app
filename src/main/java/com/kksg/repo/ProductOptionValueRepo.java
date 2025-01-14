package com.kksg.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.kksg.entity.ProductOptionValue;

public interface ProductOptionValueRepo extends JpaRepository<ProductOptionValue, Long> , JpaSpecificationExecutor<ProductOptionValue>{

	Optional<ProductOptionValue> findByIdAndIsDeletedFalse(Long id);
    
}
