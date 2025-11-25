package com.example.countries.controller;

import com.example.countries.dto.CountryDTO;
import com.example.countries.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
@RequiredArgsConstructor
public class CountryController {

	private final CountryService service;

	@GetMapping
	public List<CountryDTO> getAll() {
		return service.getAll();
	}

	@GetMapping("/{id}")
	public CountryDTO get(@PathVariable Long id) {
		return service.getById(id);
	}

	@PostMapping
	public CountryDTO create(@RequestBody CountryDTO dto) {
		return service.create(dto);
	}

	@PutMapping("/{id}")
	public CountryDTO update(@PathVariable Long id, @RequestBody CountryDTO dto) {
		return service.update(id, dto);
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		service.delete(id);
	}
}

