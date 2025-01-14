package com.kksg.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.kksg.entity.OptionValue;
import com.kksg.entity.ProductVariant;




public interface ProductVarientRepo extends JpaRepository<ProductVariant, Long>, JpaSpecificationExecutor<ProductVariant>{

	Optional<ProductVariant> findByIdAndIsDeletedFalse(Long id);	
	Optional<ProductVariant> findBySkuAndIsDeletedFalse(String sku); 
	List<ProductVariant> findByOptionValuesAndIsDeletedFalse(List<OptionValue> optionValues);
}

