package com.kksg.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.kksg.entity.ProductVariant;

public interface ProductVariantRepo extends JpaRepository<ProductVariant, Long> , JpaSpecificationExecutor<ProductVariant>{

	Optional<ProductVariant> findByIdAndIsDeletedFalse(Long id);
    
}
