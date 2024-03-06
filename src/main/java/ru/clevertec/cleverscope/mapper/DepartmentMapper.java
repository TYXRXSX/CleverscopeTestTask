package ru.clevertec.cleverscope.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.clevertec.cleverscope.dto.DepartmentDto;
import ru.clevertec.cleverscope.entity.Department;

@Mapper(componentModel = "spring")
public interface DepartmentMapper {

    DepartmentMapper INSTANCE = Mappers.getMapper(DepartmentMapper.class);

    DepartmentDto entityToDto(Department department);

    Department dtoToEntity(DepartmentDto departmentDto);

}
