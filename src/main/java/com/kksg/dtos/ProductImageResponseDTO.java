package com.kksg.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductImageResponseDTO extends BaseResponseDTO {
	
    private String imageUrl;   // URL of the image
    private String imageType;  // Type of image (e.g., "thumbnail", "main", etc.)
    private Integer imageOrder;     // Order of image
    private Long productId;
	
}
