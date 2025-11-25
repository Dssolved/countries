package com.example.countries.service;

import com.example.countries.dto.CountryDTO;
import com.example.countries.entity.Country;
import com.example.countries.mapper.CountryMapper;
import com.example.countries.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CountryService {

	private final CountryRepository repo;
	private final CountryMapper mapper;

	public List<CountryDTO> getAll() {
		return mapper.toDtoList(repo.findAll());
	}

	public CountryDTO getById(Long id) {
		Country c = repo.findById(id).orElseThrow();
		return mapper.toDto(c);
	}

	public CountryDTO create(CountryDTO dto) {
		Country entity = mapper.toEntity(dto);
		return mapper.toDto(repo.save(entity));
	}

	public CountryDTO update(Long id, CountryDTO dto) {
		Country c = repo.findById(id).orElseThrow();
		c.setName(dto.getName());
		c.setCode(dto.getCode());
		return mapper.toDto(repo.save(c));
	}

	public void delete(Long id) {
		repo.deleteById(id);
	}
}

