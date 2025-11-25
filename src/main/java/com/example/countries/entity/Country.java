package com.example.countries.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "countries")
public class Country {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String name;
	private String code;
}
