package com.example.countries.controller;

import com.example.countries.dto.ItemDTO;
import com.example.countries.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemController {

	private final ItemService service;

	@GetMapping
	public List<ItemDTO> getAll() {
		return service.getAll();
	}

	@GetMapping("/{id}")
	public ItemDTO get(@PathVariable Long id) {
		return service.getById(id);
	}

	@PostMapping
	public ItemDTO create(@RequestBody ItemDTO dto) {
		return service.create(dto);
	}

	@PutMapping("/{id}")
	public ItemDTO update(@PathVariable Long id, @RequestBody ItemDTO dto) {
		return service.update(id, dto);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
}

