package com.kksg.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
