package com.kksg.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "product_image")
public class ProductImage extends BaseEntity{

    private String imageUrl;   // The URL of the image
    private Integer imageOrder;
    private Boolean isMain = false;  // Whether the image is the main image for the product;
    
    

    @ManyToOne
    @JoinColumn(name = "variant_id") // Link to ProductVariant if image is variant-specific
    private ProductVariant variant;
}
