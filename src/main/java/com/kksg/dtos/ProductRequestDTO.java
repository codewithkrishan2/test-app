package com.kksg.dtos;

import java.util.ArrayList;
import java.util.List;

<<<<<<< HEAD
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
=======
import com.kksg.enums.ProductStatus;

>>>>>>> b3f1912b7b60dcbfafed5d27982b3a223a0ced7a
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO {
<<<<<<< HEAD
	
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
=======
	private String name;
    private String description;
    private String details;
    private Double mrp;  // Maximum Retail Price
    private Double sellingPrice;
    private Integer quantity;
    private String brand;
    private Boolean isActive = true;  // Active or not
    private ProductStatus status;  // Enum representing product status (e.g., ACTIVE, INACTIVE)
    private String fabricType;  // Fabric type (e.g., Cotton, Lace)
    private Long categoryId;  // Foreign key reference to Category
    private String mainImageUrl;
    
    private List<String> imageUrls;  // List of image URLs for the product images
    private List<ProductOptionRequestDTO> options;  // List of product options (like sizes, colors, etc.)
    private List<ProductVariantRequestDTO> variants;  // List of product variants (e.g., 32A, 34B, etc.)

    // Inner DTO for ProductOption
    @Getter
    @Setter
    public static class ProductOptionRequestDTO {
        private String optionName;  // e.g., Size, Color
        private List<String> optionValues;  // e.g., [32A, 34B, Red, Black]
    }

    // Inner DTO for ProductVariant
    @Getter
    @Setter
    public static class ProductVariantRequestDTO {
        private String size;  // e.g., 32A, 34C
        private String type;  // e.g., Sports, Full Coverage
        private Integer quantity;  // Stock quantity for the variant
        private Double sellingPrice;  // Selling price for the variant
        private Boolean isActive = true;  // Whether this variant is active
    }
>>>>>>> b3f1912b7b60dcbfafed5d27982b3a223a0ced7a
}