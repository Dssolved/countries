package com.example.countries.mapper;

import com.example.countries.dto.CountryDTO;
import com.example.countries.entity.Country;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CountryMapper {
	CountryDTO toDto(Country country);

	Country toEntity(CountryDTO dto);

	List<CountryDTO> toDtoList(List<Country> list);
}

