package ru.clevertec.cleverscope.mapper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.cleverscope.dto.DepartmentDto;
import ru.clevertec.cleverscope.entity.Department;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class DepartmentMapperTest {

    @InjectMocks
    private DepartmentMapper departmentMapper = DepartmentMapper.INSTANCE;

    @Test
    public void entityToDto() {
        Department department = new Department();
        department.setId(1L);
        department.setName("IT");

        DepartmentDto departmentDto = departmentMapper.entityToDto(department);

        assertEquals(department.getId(), departmentDto.getId());
        assertEquals(department.getName(), departmentDto.getName());
    }

    @Test
    public void dtoToEntity() {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(1L);
        departmentDto.setName("IT");

        Department department = departmentMapper.dtoToEntity(departmentDto);

        assertEquals(departmentDto.getId(), department.getId());
        assertEquals(departmentDto.getName(), department.getName());
    }
}