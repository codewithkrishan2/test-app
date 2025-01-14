package com.kksg.dtos;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO extends BaseResponseDTO {
    private String sku;

    private String name;

    private String description;

    private String details;

    private String brand;

    private Boolean active;

    private String categoryName;

    private List<ProductVariantResponseDTO> variants = new ArrayList<>();

    private List<ProductImageResponseDTO> images = new ArrayList<>();
}
