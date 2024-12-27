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
        preProcessBeforeSave(entity);
        logger.debug("Saving entity");
        return repository.save(entity);
    }

    public List<T> findAll() {
//        return specificationExecutor.findAll(prepareFilter());
    	List<T> entities = specificationExecutor.findAll(prepareFilter());
		return postProcessAfterGetData(entities);
    }

    public Optional<T> findById(ID id) {
//        return specificationExecutor.findOne(prepareFilter().and(byId(id)));
    	Optional<T> entity = specificationExecutor.findOne(prepareFilter().and(byId(id)));
        return entity.map(this::postProcessAfterGetById);  // Post-process individual entity
    }

    public void delete(T entity) {
        entity.setDeleted(true);
        repository.save(entity);
    }

    public void deleteById(ID id) {
        findById(id).ifPresent(this::delete);
    }

    protected void preProcessBeforeSave(T entity) {
        // Override in subclasses if needed
    }

    protected Specification<T> prepareFilter() {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("deleted"), false);
    }

    private Specification<T> byId(ID id) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("id"), id);
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