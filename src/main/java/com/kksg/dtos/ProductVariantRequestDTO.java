package com.kksg.dtos;

import java.util.ArrayList;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductVariantRequestDTO {
    @NotBlank
    private String sku;

    @NotNull
    @Positive
    private Double mrp;

    @NotNull
    @Positive
    private Double sellingPrice;

    @PositiveOrZero
    private int quantity;

    private List<Long> optionValueIds = new ArrayList<>(); // IDs of selected option values

    private List<ProductImageRequestDTO> images = new ArrayList<>(); // Variant-specific images
}
