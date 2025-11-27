package com.example.countries.service;

import com.example.countries.dto.ItemDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

@SpringBootTest
class ItemServiceTest {

	@Autowired
	private ItemService itemService;

	@Test
	void getAllItemsTest() {
		List<ItemDTO> items = itemService.getAll();

		Assertions.assertNotNull(items);
		Assertions.assertNotEquals(0, items.size());

		for (ItemDTO item : items) {
			Assertions.assertNotNull(item);
			Assertions.assertNotNull(item.getId());
			Assertions.assertNotNull(item.getName());
			Assertions.assertNotNull(item.getManufacturerId());
		}
	}

	@Test
	void getItemByIdTest() {
		List<ItemDTO> items = itemService.getAll();
		Random random = new Random();
		int randomIndex = random.nextInt(items.size());

		Long id = items.get(randomIndex).getId();

		ItemDTO dto = itemService.getById(id);

		Assertions.assertNotNull(dto);
		Assertions.assertNotNull(dto.getId());
		Assertions.assertNotNull(dto.getName());
		Assertions.assertNotNull(dto.getManufacturerId());

		Assertions.assertThrows(Exception.class, () -> itemService.getById(-1L));
	}

	@Test
	void createItemTest() {
		ItemDTO dto = new ItemDTO();
		dto.setName("Test Item");
		dto.setPrice(999);
		dto.setQuantity(55);
		dto.setManufacturerId(1L);

		ItemDTO created = itemService.create(dto);

		Assertions.assertNotNull(created);
		Assertions.assertNotNull(created.getId());
		Assertions.assertNotNull(created.getManufacturerId());

		Assertions.assertEquals(dto.getName(), created.getName());
		Assertions.assertEquals(dto.getPrice(), created.getPrice());
		Assertions.assertEquals(dto.getQuantity(), created.getQuantity());

		ItemDTO fromDb = itemService.getById(created.getId());

		Assertions.assertNotNull(fromDb);
		Assertions.assertEquals(created.getId(), fromDb.getId());
	}

	@Test
	void updateItemTest() {
		List<ItemDTO> items = itemService.getAll();
		Long id = items.get(0).getId();

		ItemDTO dto = new ItemDTO();
		dto.setName("Updated Name");
		dto.setPrice(12345);
		dto.setQuantity(77);
		dto.setManufacturerId(1L);

		ItemDTO updated = itemService.update(id, dto);

		Assertions.assertNotNull(updated);
		Assertions.assertEquals("Updated Name", updated.getName());
		Assertions.assertEquals(12345, updated.getPrice());
		Assertions.assertEquals(77, updated.getQuantity());
		Assertions.assertEquals(1L, updated.getManufacturerId());

		ItemDTO fromDb = itemService.getById(id);

		Assertions.assertNotNull(fromDb);
		Assertions.assertEquals("Updated Name", fromDb.getName());
	}

	@Test
	void deleteItemTest() {
		ItemDTO dto = new ItemDTO();
		dto.setName("Del test");
		dto.setPrice(100);
		dto.setQuantity(10);
		dto.setManufacturerId(1L);

		ItemDTO created = itemService.create(dto);

		Assertions.assertNotNull(created.getId());

		Long id = created.getId();
		itemService.delete(id);

		Assertions.assertThrows(Exception.class, () -> itemService.getById(id));
	}
}
