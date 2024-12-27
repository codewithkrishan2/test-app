package com.kksg.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.kksg.entity.Product;
import com.kksg.entity.Size;

public interface SizeRepo extends JpaRepository<Size, Long>, JpaSpecificationExecutor<Size>{

	Optional<Size> findByIdAndIsDeletedFalse(Long id);
	List<Size> findByProductAndIsDeletedFalse(Product product);
}
