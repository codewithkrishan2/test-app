package com.kksg.dtos;

import java.util.List;

import com.kksg.enums.ProductStatus;

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

    private ProductStatus status;  // Enum representing product status (e.g., ACTIVE, INACTIVE)
    private String mainImageUrl;
    
    private List<String> imageUrls;  // List of image URLs for the product images
    private List<OptionRequestDTO> options;  // List of product options (like sizes, colors, etc.)
    private List<ProductVariantRequestDTO> variants;  // List of product variants (e.g., 32A, 34B, etc.)

    
}