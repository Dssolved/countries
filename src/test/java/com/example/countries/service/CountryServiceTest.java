package com.example.countries.service;

import com.example.countries.dto.CountryDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Random;

@SpringBootTest
class CountryServiceTest {

	@Autowired
	private CountryService countryService;


	@Test
	void getAllCountriesTest() {
		List<CountryDTO> countries = countryService.getAll();

		Assertions.assertNotNull(countries);
		Assertions.assertNotEquals(0, countries.size());

		for (CountryDTO dto : countries) {
			Assertions.assertNotNull(dto);
			Assertions.assertNotNull(dto.getId());
			Assertions.assertNotNull(dto.getName());
			Assertions.assertNotNull(dto.getCode());
		}
	}


	@Test
	void getCountryByIdTest() {
		Random random = new Random();
		int idx = random.nextInt(countryService.getAll().size());
		Long id = countryService.getAll().get(idx).getId();

		CountryDTO dto = countryService.getById(id);

		Assertions.assertNotNull(dto);
		Assertions.assertNotNull(dto.getId());
		Assertions.assertNotNull(dto.getName());
		Assertions.assertNotNull(dto.getCode());

		Assertions.assertThrows(Exception.class, () -> countryService.getById(-1L));
	}


	@Test
	void createCountryTest() {
		CountryDTO input = CountryDTO.builder()
			.name("Spain")
			.code("ES")
			.build();

		CountryDTO created = countryService.create(input);

		Assertions.assertNotNull(created);
		Assertions.assertNotNull(created.getId());
		Assertions.assertEquals(input.getName(), created.getName());
		Assertions.assertEquals(input.getCode(), created.getCode());

		CountryDTO check = countryService.getById(created.getId());
		Assertions.assertNotNull(check);
		Assertions.assertEquals(created.getName(), check.getName());
		Assertions.assertEquals(created.getCode(), check.getCode());
	}


	@Test
	void updateCountryTest() {
		Random random = new Random();
		int idx = random.nextInt(countryService.getAll().size());
		Long id = countryService.getAll().get(idx).getId();

		CountryDTO updatedData = CountryDTO.builder()
			.id(id)
			.name("Updated Country Name")
			.code("UP")
			.build();

		CountryDTO updated = countryService.update(id, updatedData);

		Assertions.assertNotNull(updated);
		Assertions.assertEquals(id, updated.getId());
		Assertions.assertEquals(updatedData.getName(), updated.getName());
		Assertions.assertEquals(updatedData.getCode(), updated.getCode());

		CountryDTO check = countryService.getById(updated.getId());
		Assertions.assertNotNull(check);
		Assertions.assertEquals(updated.getName(), check.getName());
		Assertions.assertEquals(updated.getCode(), check.getCode());

		Assertions.assertThrows(Exception.class, () -> countryService.update(-1L, updatedData));
	}


	@Test
	void deleteCountryTest() {
		CountryDTO dto = new CountryDTO();
		dto.setName("Delete Test Country");
		dto.setCode("DEL");

		CountryDTO created = countryService.create(dto);

		Assertions.assertNotNull(created.getId());

		Long id = created.getId();

		countryService.delete(id);

		Assertions.assertThrows(Exception.class, () -> countryService.getById(id));
	}

}
