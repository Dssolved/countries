package com.example.countries.service;

import com.example.countries.dto.ItemDTO;
import com.example.countries.entity.Country;
import com.example.countries.entity.Item;
import com.example.countries.mapper.ItemMapper;
import com.example.countries.repository.CountryRepository;
import com.example.countries.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

	private final ItemRepository repo;
	private final CountryRepository countryRepo;
	private final ItemMapper mapper;

	public List<ItemDTO> getAll() {
		return mapper.toDtoList(repo.findAll());
	}

	public ItemDTO getById(Long id) {
		return mapper.toDto(repo.findById(id).orElseThrow());
	}

	public ItemDTO create(ItemDTO dto) {
		Item item = mapper.toEntity(dto);

		Country country = countryRepo.findById(dto.getManufacturerId())
			.orElseThrow();

		item.setManufacturer(country);

		return mapper.toDto(repo.save(item));
	}

	public ItemDTO update(Long id, ItemDTO dto) {
		Item item = repo.findById(id).orElseThrow();

		item.setName(dto.getName());
		item.setPrice(dto.getPrice());
		item.setQuantity(dto.getQuantity());

		Country newCountry = countryRepo.findById(dto.getManufacturerId())
			.orElseThrow();

		item.setManufacturer(newCountry);

		return mapper.toDto(repo.save(item));
	}

	public void delete(Long id) {
		repo.deleteById(id);
	}
}

