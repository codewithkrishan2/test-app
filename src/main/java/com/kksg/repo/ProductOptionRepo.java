package com.kksg.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.kksg.entity.Category;
import com.kksg.entity.Product;
import com.kksg.entity.ProductOption;

public interface ProductOptionRepo extends JpaRepository<ProductOption, Long>, JpaSpecificationExecutor<ProductOption>{

	Optional<ProductOption> findByIdAndIsDeletedFalse(Long id);

	Optional<ProductOption> findByNameAndProductAndIsDeletedFalse(String name, Product product);
	
}
