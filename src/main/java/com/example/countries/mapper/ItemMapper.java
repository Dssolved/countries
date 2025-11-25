package com.example.countries.mapper;

import com.example.countries.dto.ItemDTO;
import com.example.countries.entity.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper {

	@Mapping(source = "manufacturer.id", target = "manufacturerId")
	ItemDTO toDto(Item item);

	@Mapping(source = "manufacturerId", target = "manufacturer.id")
	Item toEntity(ItemDTO dto);

	List<ItemDTO> toDtoList(List<Item> items);
}
