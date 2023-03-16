package com.mobile.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "inventories")
public class Inventory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
	
	@Column(name = "quantity")
	private Integer quantity;
	
	@OneToOne(mappedBy = "inventory")
	@JsonIgnore
	private Cloth cloth;

	public Inventory(Integer quantity) {
		super();
		this.quantity = quantity;
	}
	
	
	
}
