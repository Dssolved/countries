package com.example.countries.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {
	private Long id;
	private String name;
	private int price;
	private int quantity;
	private Long manufacturerId;
}

