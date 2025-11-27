package com.example.countries.mapper;

import com.example.countries.dto.CountryDTO;
import com.example.countries.entity.Country;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.util.ArrayList;
import java.util.List;

class CountryMapperTest {

	CountryMapper countryMapper = Mappers.getMapper(CountryMapper.class);

	@Test
	void convertEntityToDtoTest() {
		Country country = new Country(1L, "USA", "US");

		CountryDTO dto = countryMapper.toDto(country);

		Assertions.assertNotNull(dto);
		Assertions.assertNotNull(dto.getId());
		Assertions.assertNotNull(dto.getName());
		Assertions.assertNotNull(dto.getCode());

		Assertions.assertEquals(country.getId(), dto.getId());
		Assertions.assertEquals(country.getName(), dto.getName());
		Assertions.assertEquals(country.getCode(), dto.getCode());
	}

	@Test
	void convertDtoToEntityTest() {
		CountryDTO dto = CountryDTO.builder()
			.id(1L)
			.name("Japan")
			.code("JP")
			.build();

		Country country = countryMapper.toEntity(dto);

		Assertions.assertNotNull(country);

		Assertions.assertNotNull(country.getId());
		Assertions.assertNotNull(country.getName());
		Assertions.assertNotNull(country.getCode());

		Assertions.assertEquals(dto.getId(), country.getId());
		Assertions.assertEquals(dto.getName(), country.getName());
		Assertions.assertEquals(dto.getCode(), country.getCode());
	}

	@Test
	void convertEntityListToDtoListTest() {
		List<Country> list = new ArrayList<>();
		list.add(new Country(1L, "USA", "US"));
		list.add(new Country(2L, "Japan", "JP"));
		list.add(new Country(3L, "Germany", "DE"));

		List<CountryDTO> dtos = countryMapper.toDtoList(list);

		Assertions.assertNotNull(dtos);
		Assertions.assertEquals(list.size(), dtos.size());

		for (int i = 0; i < list.size(); i++) {
			Country c = list.get(i);
			CountryDTO d = dtos.get(i);

			Assertions.assertNotNull(d);
			Assertions.assertEquals(c.getId(), d.getId());
			Assertions.assertEquals(c.getName(), d.getName());
			Assertions.assertEquals(c.getCode(), d.getCode());
		}
	}
}
