package com.example.countries.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "items")
public class Item {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private int price;
	private int quantity;

	@ManyToOne
	@JoinColumn(name = "manufacturer_id")
	private Country manufacturer;
}

