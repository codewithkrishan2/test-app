package com.kksg.entity;

import java.util.ArrayList;
import java.util.List;

import com.kksg.enums.ProductStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
@Table(name = "product")
public class Product extends BaseEntity {
	
	private String name;
    private String description;
    private String details;
    private Double mrp;
    private Double sellingPrice;
    private Integer quantity;
    private String brand;
    private Boolean isActive = true;
    
    @Enumerated(value = EnumType.STRING)
    private ProductStatus status;

    private String color;
    private String fabricType;              // Fabric type (e.g., Cotton, Lace)
    private String paddingType;             // Padding type
    
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductImage> images = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Size> sizes = new ArrayList<>();
  
    
}
