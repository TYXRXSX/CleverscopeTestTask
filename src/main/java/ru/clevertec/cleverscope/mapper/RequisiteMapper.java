package ru.clevertec.cleverscope.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.clevertec.cleverscope.dto.RequisiteDto;
import ru.clevertec.cleverscope.entity.Requisite;

@Mapper(componentModel = "spring")
public interface RequisiteMapper {

    RequisiteMapper INSTANCE = Mappers.getMapper(RequisiteMapper.class);

    RequisiteDto entityToDto(Requisite requisite);

    Requisite dtoToEntity(RequisiteDto requisiteDto);

}
