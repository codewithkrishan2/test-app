package com.kksg.dtos;

import java.util.List;

import com.kksg.enums.ProductStatus;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO {
	   	@NotBlank(message = "Product name cannot be empty")
	    @Size(min = 3, max = 100, message = "Product name should be between 3 and 100 characters")
	    private String name;

	    @NotBlank(message = "Product description cannot be empty")
	    @Size(min = 10, max = 500, message = "Product description should be between 10 and 500 characters")
	    private String description;

	    private String details;

	    @NotNull(message = "MRP cannot be null")
	    @DecimalMin(value = "0.01", message = "MRP should be greater than 0")
	    private Double mrp;

	    @NotNull(message = "Selling price cannot be null")
	    @DecimalMin(value = "0.01", message = "Selling price should be greater than 0")
	    private Double sellingPrice;

		@NotNull(message = "Quantity cannot be null")
	    @Min(value = 0, message = "Quantity cannot be negative")
	    private Integer quantity;

	    private String brand;

	    private Boolean isActive = true;

	    @NotNull(message = "Product status cannot be null")
	    private ProductStatus status;

	    private String color;

	    @NotBlank(message = "Fabric type cannot be empty")
	    private String fabricType;

	    @NotBlank(message = "Padding type cannot be empty")
	    private String paddingType;

	    @NotNull(message = "Category cannot be null")
	    private Long categoryId;  // Assuming category is passed as an ID

	    @NotNull(message = "Sizes cannot be null")
	    private List<SizeRequestDTO> sizes;

	    @NotNull(message = "Images cannot be null")
	    private List<String> imageLinks;  // Assuming image links are passed as strings (URLs)
	}