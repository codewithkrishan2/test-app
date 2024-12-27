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
@Table(name = "size")
public class Size extends BaseEntity {

	private String sizeLabel; // e.g., "S", "M", "L", "32B", "36C", "34D"
	private Double bustMeasurement; // For br.., bust measurement in inches/cm (optional)
	private Double waistMeasurement; // For pant..., waist measurement in inches/cm (optional)
	private Double hipsMeasurement; // For pant..., hips measurement in inches/cm (optional)
	private String sizeType; // "BR...", "PANT..", etc.
	private Boolean underwire;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

}
