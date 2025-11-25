package com.example.countries.dto;

import lombok.Data;

@Data
public class ItemDTO {
	private Long id;
	private String name;
	private int price;
	private int quantity;
	private Long manufacturerId;
}

