package com.kksg.dtos;

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
public class ProductImageRequestDTO {

    @NotNull(message = "Product ID cannot be null")
    private Long productId;

    @NotNull(message = "Image URL cannot be null")
    @Size(min = 5, message = "Image URL must be at least 5 characters long")
    private String imageUrl;  // The URL of the image

    @NotNull(message = "Image Type cannot be null")
    private String imageType; // Type of image (e.g., "thumbnail", "main")

    private Integer imageOrder; 
	
}
