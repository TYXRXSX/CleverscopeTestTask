package ru.clevertec.cleverscope.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import ru.clevertec.cleverscope.dto.EmployeeDto;
import ru.clevertec.cleverscope.entity.Employee;

@Mapper(uses = {DepartmentMapper.class, RequisiteMapper.class}, componentModel = "spring")
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    EmployeeDto entityToDto(Employee employee);

    Employee dtoToEntity(EmployeeDto employeeDto);
}
