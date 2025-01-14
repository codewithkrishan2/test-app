package com.kksg.dtos;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO {
	
	@NotBlank
	private String name;

	@NotBlank
	private String description;

	private String details;

	@NotBlank
	private String brand;

	@NotNull
	private Boolean active;

	@NotNull
	private Long categoryId;

	private List<ProductVariantRequestDTO> variants = new ArrayList<>();
}