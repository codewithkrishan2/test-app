package com.kksg.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.kksg.entity.ProductImage;

public interface ProductImageRepo extends JpaRepository<ProductImage, Long>, JpaSpecificationExecutor<ProductImage>{

	Optional<ProductImage> findByIdAndIsDeletedFalse(Long id);
    
}