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
@Table(name = "product_option_value")
public class ProductOptionValue extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "option_id", nullable = false)
    private ProductOption option;

    private String value;  // Option value (e.g., "Red", "34", "C")
}
