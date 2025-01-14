package com.kksg.dtos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductVariantResponseDTO extends BaseResponseDTO {

    private String sku;

    private BigDecimal mrp;

    private BigDecimal sellingPrice;

    private int quantity;

    private List<OptionValueResponseDTO> optionValues = new ArrayList<>();

    private List<ProductImageResponseDTO> images = new ArrayList<>();
}
