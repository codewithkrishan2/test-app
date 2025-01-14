package com.kksg.entity;

import java.util.ArrayList;
import java.util.List;

import com.kksg.enums.ProductStatus;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
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
public class Product  extends BaseEntity {
	
	private String sku;
	private String name;
    private String description;
    private String details;
    private String brand;
    
    @Enumerated(value = EnumType.STRING)
<<<<<<< HEAD
    private ProductStatus status; //	AVAILABLE, OUT_OF_STOCK, DISCONTINUED
    
=======
    private ProductStatus status;

    private String fabricType;  // e.g., Cotton, Lace

>>>>>>> b3f1912b7b60dcbfafed5d27982b3a223a0ced7a
    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

<<<<<<< HEAD
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductVariant> variants = new ArrayList<>();
    
=======
    private String mainImageUrl;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductImage> images = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductOption> options = new ArrayList<>();

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<ProductVariant> variants = new ArrayList<>();  
>>>>>>> b3f1912b7b60dcbfafed5d27982b3a223a0ced7a
}
