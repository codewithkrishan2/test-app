package com.kksg.dtos;

import java.util.List;

import com.kksg.enums.ProductStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponseDTO extends BaseResponseDTO {
	private String name;
    private String description;
    private String details;
    private Double mrp;
    private Double sellingPrice;
    private Integer quantity;
    private String brand;
    private Boolean isActive;
    private ProductStatus status;
    private String color;
    private String fabricType;
    private String paddingType;
    private CategoryRequestDTO category; 
    private List<String> imageLinks;
    private List<SizeResponseDTO> sizes;
}
