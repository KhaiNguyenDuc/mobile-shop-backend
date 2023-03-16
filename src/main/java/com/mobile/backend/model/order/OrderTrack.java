package com.mobile.backend.model.order;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "order_track")
public class OrderTrack {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	private Long id;
}
