package com.kksg.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.kksg.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product>{

	Optional<Product> findByIdAndIsDeletedFalse(Long id);
    
}
