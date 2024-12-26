package com.kksg.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.kksg.contants.Status;
import com.kksg.entity.BaseEntity;
import com.kksg.service.BaseService;
import com.kksg.util.ApiResponse;

import jakarta.validation.Valid;

public abstract class BaseController<T extends BaseEntity, R, U> { 
    // T: Entity, R: Response DTO, U: Request DTO

    private final BaseService<T, Long> baseService;

    private String notFoundMessage = "No record found";
    
    protected BaseController(BaseService<T, Long> baseService) {
        this.baseService = baseService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<R>> create(@Valid @RequestBody U createRequest) {
        T entity = mapToEntity(createRequest);
        T savedEntity = baseService.save(entity);
        return ResponseEntity.ok(new ApiResponse<>(Status.SUCCESS, null, "Saved successfully", mapToResponse(savedEntity)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<R>>> getAll() {
        List<T> entities = baseService.findAll();
        List<R> responseList = entities.stream().map(this::mapToResponse).toList();
        return ResponseEntity.ok(new ApiResponse<>(Status.SUCCESS, null, "Fetched successfully",responseList));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<R>> getById(@PathVariable Long id) {
        return baseService.findById(id)
                .map(entity -> ResponseEntity.ok(new ApiResponse<>(Status.SUCCESS, null,"Fetched successfully", mapToResponse(entity))))
                .orElse(ResponseEntity.status(404).body(new ApiResponse<>(Status.FAILED, notFoundMessage, null, null)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<R>> update(@PathVariable Long id, @Valid @RequestBody U updateRequest) {
        return baseService.findById(id)
                .map(existingEntity -> {
                    T updatedEntity = mapToEntity(updateRequest);
                    updatedEntity.setId(id);
                    T savedEntity = baseService.save(updatedEntity);
                    return ResponseEntity.ok(new ApiResponse<>(Status.SUCCESS, null,"Updated successfully", mapToResponse(savedEntity)));
                })
                .orElse(ResponseEntity.status(404).body(new ApiResponse<>(Status.FAILED, notFoundMessage, null,null)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        return baseService.findById(id)
                .map(entity -> {
                    baseService.delete(entity);
                    return ResponseEntity.ok(new ApiResponse<Void>(Status.SUCCESS, null, "Deleted successfully",null));
                })
                .orElse(ResponseEntity.status(404).body(new ApiResponse<Void>(Status.FAILED, notFoundMessage,null , null)));
    }

    // Abstract methods for mapping between DTOs and entities
    protected abstract T mapToEntity(U dto);

    protected abstract R mapToResponse(T entity);
}