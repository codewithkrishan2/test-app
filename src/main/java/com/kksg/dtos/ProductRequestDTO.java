package com.kksg.dtos;

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

    private List<String> imageUrls;  // List of image URLs for the product images
    private List<OptionRequestDTO> options;  // List of product options (like sizes, colors, etc.)
    private List<ProductVariantRequestDTO> variants;  // List of product variants (e.g., 32A, 34B, etc.)

    // Inner DTO for ProductOption
//    @Getter
//    @Setter
//    public static class ProductOptionRequestDTO {
//        private String optionName;  // e.g., Size, Color
//        private List<String> optionValues;  // e.g., [32A, 34B, Red, Black]
//    }

    // Inner DTO for ProductVariant
//    @Getter
//    @Setter
//    public static class ProductVariantRequestDTO {
//        private String size;  // e.g., 32A, 34C
//        private String type;  // e.g., Sports, Full Coverage
//        private Integer quantity;  // Stock quantity for the variant
//        private Double sellingPrice;  // Selling price for the variant
//        private Boolean isActive = true;  // Whether this variant is active
//    }
}