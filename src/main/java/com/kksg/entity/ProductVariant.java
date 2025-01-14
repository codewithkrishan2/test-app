package com.kksg.entity;

<<<<<<< HEAD
import java.math.BigDecimal;
=======
>>>>>>> b3f1912b7b60dcbfafed5d27982b3a223a0ced7a
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
<<<<<<< HEAD
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
=======
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
>>>>>>> b3f1912b7b60dcbfafed5d27982b3a223a0ced7a
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
<<<<<<< HEAD
@Table(name = "product_variants")
public class ProductVariant extends BaseEntity {

	@Column(nullable = false, unique = true)
	private String sku; // Unique SKU for the variant (e.g., "T001-RED-M")

    private BigDecimal mrp;
	private BigDecimal sellingPrice;
	
	@Column(nullable = false)
    private int quantity;
	
	@ManyToOne
	@JoinColumn(name = "product_id", nullable = false)
	private Product product;

	@ManyToMany
	@JoinTable(
			name = "variant_option_values", 
			joinColumns = @JoinColumn(name = "variant_id"), 
			inverseJoinColumns = @JoinColumn(name = "option_value_id"))
	private List<OptionValue> optionValues = new ArrayList<>();


	@OneToMany(mappedBy = "variant", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ProductImage> images = new ArrayList<>(); // Variant-specific images

}
=======
@Table(name = "product_variant")
public class ProductVariant extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
        name = "variant_option_values",
        joinColumns = @JoinColumn(name = "variant_id"),
        inverseJoinColumns = @JoinColumn(name = "option_value_id")
    )
    private List<ProductOptionValue> optionValues = new ArrayList<>();

    private Double mrp;
    private Double sellingPrice;
    private Integer inventoryQuantity;

    private Boolean isOnSale = false;  // Indicates if the variant is part of a sale
}

>>>>>>> b3f1912b7b60dcbfafed5d27982b3a223a0ced7a
