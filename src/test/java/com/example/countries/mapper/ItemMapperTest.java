package com.example.countries.mapper;

import com.example.countries.dto.ItemDTO;
import com.example.countries.entity.Country;
import com.example.countries.entity.Item;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

class ItemMapperTest {

	ItemMapper itemMapper = Mappers.getMapper(ItemMapper.class);

	@Test
	void convertEntityToDtoTest() {
		Item item = new Item(1L, "Iphone 16", 600000, 100, new Country(1L, "USA", "US"));

		ItemDTO itemDto = itemMapper.toDto(item);

		Assertions.assertNotNull(itemDto);

		Assertions.assertNotNull(itemDto.getId());
		Assertions.assertNotNull(itemDto.getName());

		Assertions.assertEquals(item.getId(), itemDto.getId());
		Assertions.assertEquals(item.getName(), itemDto.getName());
		Assertions.assertEquals(item.getPrice(), itemDto.getPrice());
		Assertions.assertEquals(item.getQuantity(), itemDto.getQuantity());
	}


	@Test
	void convertDtoToEntityTest() {
		ItemDTO itemDto = ItemDTO
			.builder()
			.id(1L)
			.name("Iphone 16")
			.price(600000)
			.quantity(100)
			.build();

		Item item = itemMapper.toEntity(itemDto);

		Assertions.assertNotNull(item);

		Assertions.assertNotNull(item.getId());
		Assertions.assertNotNull(item.getName());

		Assertions.assertEquals(itemDto.getId(), item.getId());
		Assertions.assertEquals(itemDto.getName(), item.getName());
		Assertions.assertEquals(itemDto.getPrice(), item.getPrice());
		Assertions.assertEquals(itemDto.getQuantity(), item.getQuantity());
	}

	@Test
	void convertEntityListToDtoListTest() {
		List<Item> items = new ArrayList<>();
		items.add(new Item(1L, "Iphone 16", 600000, 100, new Country(1L, "USA", "US")));
		items.add(new Item(2L, "Iphone 16S", 700000, 80, new Country(1L, "USA", "US")));
		items.add(new Item(3L, "Iphone 16 Pro", 900000, 50, new Country(1L, "USA", "US")));
		items.add(new Item(4L, "Iphone 16 Pro Max", 1000000, 20, new Country(1L, "USA", "US")));

		List<ItemDTO> itemDtoList = itemMapper.toDtoList(items);

		Assertions.assertNotNull(itemDtoList);

		Assertions.assertNotEquals(0, itemDtoList.size());

		Assertions.assertEquals(items.size(), itemDtoList.size());

		for (int i = 0; i < items.size(); i++) {
			Item item = items.get(i);
			ItemDTO itemDto = itemDtoList.get(i);

			Assertions.assertNotNull(itemDto);

			Assertions.assertNotNull(itemDto.getId());
			Assertions.assertNotNull(itemDto.getName());

			Assertions.assertEquals(item.getId(), itemDto.getId());
			Assertions.assertEquals(item.getName(), itemDto.getName());
			Assertions.assertEquals(item.getPrice(), itemDto.getPrice());
			Assertions.assertEquals(item.getQuantity(), itemDto.getQuantity());
		}
	}
}