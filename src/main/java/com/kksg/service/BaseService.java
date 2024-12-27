package com.kksg.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.kksg.entity.BaseEntity;

public abstract class BaseService<T extends BaseEntity, ID> {

	private static final Logger logger = LoggerFactory.getLogger(BaseService.class);
	private final JpaRepository<T, ID> repository;
	private final JpaSpecificationExecutor<T> specificationExecutor;

	protected BaseService(JpaRepository<T, ID> repository, JpaSpecificationExecutor<T> specificationExecutor) {
		this.repository = repository;
		this.specificationExecutor = specificationExecutor;
	}

	public T save(T entity) {
		T model = preProcessBeforeSave(entity);
		logger.debug("Saving entity");
		return createPostProcess(repository.save(model));
	}

	public List<T> findAll() {
		List<T> entities = specificationExecutor.findAll(prepareFilter());
		return postProcessAfterGetData(entities);
	}

	public Optional<T> findById(ID id) {
		Optional<T> entity = specificationExecutor.findOne(prepareFilter().and(byId(id)));
		return entity.map(this::postProcessAfterGetById); // Post-process individual entity
	}

	public void updateDeleteFlag(T entity) {
		entity.setDeleted(true);
		logger.info("Updating Delete Flag entity");
		repository.save(entity);
	}

	public void deleteById(ID id) {
		findById(id).ifPresent(this::delete);
	}

	public void delete(T entity) {
		logger.info("Permanent Deleting entity by id: {}", entity.getId());
		repository.delete(entity);
	}

	protected Specification<T> prepareFilter() {
		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("deleted"), false);
	}

	private Specification<T> byId(ID id) {
		return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), id);
	}

	protected T preProcessBeforeSave(T entity) {
		// Override in subclasses if needed
		return entity;
	}

	protected T createPostProcess(T model) {
		// Override in subclass for post-processing
		return model;
	}

	// Abstract post-processing method after fetching data
	protected List<T> postProcessAfterGetData(List<T> result) {
		return result;
	}

	// Abstract post-processing method after fetching a single entity by ID
	protected T postProcessAfterGetById(T result) {
		// Default implementation (could be overridden by subclasses)
		return result;
	}
}