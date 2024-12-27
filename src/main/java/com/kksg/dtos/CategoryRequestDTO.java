package com.kksg.dtos;

import com.kksg.entity.Category;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryRequestDTO{
	
	@NotBlank(message = "Name is required")
	private String name;
	
	private String description;
	
    private Category parentCategory;
    
}
