package ru.clevertec.cleverscope.service.impl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.clevertec.cleverscope.dto.DepartmentDto;
import ru.clevertec.cleverscope.entity.Department;
import ru.clevertec.cleverscope.exception.ResourceNotFoundException;
import ru.clevertec.cleverscope.mapper.DepartmentMapper;
import ru.clevertec.cleverscope.repository.DepartmentRepo;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
public class DepartmentServiceImplTest {

    @Mock
    private DepartmentRepo departmentRepo;

    @Mock
    private DepartmentMapper departmentMapper;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @Test
    public void saveDepartment_ReturnsDepartmentDto() {
        DepartmentDto departmentDto = new DepartmentDto();
        Department department = new Department();
        when(departmentMapper.dtoToEntity(departmentDto)).thenReturn(department);

        Department savedDepartment = new Department();
        when(departmentRepo.save(department)).thenReturn(savedDepartment);

        DepartmentDto savedDepartmentDto = new DepartmentDto();
        when(departmentMapper.entityToDto(savedDepartment)).thenReturn(savedDepartmentDto);

        DepartmentDto result = departmentService.saveDepartment(departmentDto);

        assertEquals(savedDepartmentDto, result);
        verify(departmentRepo).save(department);
    }

    @Test
    public void deleteDepartmentById_DeletesDepartmentAndUpdateDeletedDate() {
        Long id = 1L;
        Department department = new Department();
        when(departmentRepo.findById(id)).thenReturn(Optional.of(department));

        DepartmentDto departmentDto = new DepartmentDto();
        when(departmentMapper.entityToDto(department)).thenReturn(departmentDto);

        departmentService.deleteDepartmentById(id);

        assertEquals(LocalDate.now(), department.getDeletedDate());
        verify(departmentRepo).save(department);
    }

    @Test
    public void deleteDepartmentById_ThrowsResourceNotFoundException_WhenDepartmentNotFound() {
        Long id = 1L;
        when(departmentRepo.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> departmentService.deleteDepartmentById(id));
    }

    @Test
    public void getDepartmentById_ReturnsDepartmentDto_WhenDepartmentExists() {
        Long id = 1L;
        Department department = new Department();
        when(departmentRepo.findById(id)).thenReturn(Optional.of(department));

        DepartmentDto departmentDto = new DepartmentDto();
        when(departmentMapper.entityToDto(department)).thenReturn(departmentDto);

        DepartmentDto result = departmentService.getDepartmentById(id);

        assertEquals(departmentDto, result);
    }

    @Test
    public void getDepartmentById_ThrowsResourceNotFoundException_WhenDepartmentNotFound() {
        Long id = 1L;
        when(departmentRepo.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> departmentService.getDepartmentById(id));
    }

    @Test
    public void putDepartment_ReturnsUpdatedDepartmentDto_WhenDepartmentExists() {
        DepartmentDto departmentDto = new DepartmentDto();
        Department department = new Department();
        when(departmentMapper.dtoToEntity(departmentDto)).thenReturn(department);

        Long id = 1L;
        departmentDto.setId(id);
        when(departmentRepo.existsById(id)).thenReturn(true);

        Department updatedDepartment = new Department();
        when(departmentRepo.save(department)).thenReturn(updatedDepartment);

        DepartmentDto updatedDepartmentDto = new DepartmentDto();
        when(departmentMapper.entityToDto(updatedDepartment)).thenReturn(updatedDepartmentDto);

        DepartmentDto result = departmentService.putDepartment(departmentDto);

        assertEquals(updatedDepartmentDto, result);
        verify(departmentRepo).save(department);
    }

    @Test
    public void putDepartment_ThrowsResourceNotFoundException_WhenDepartmentNotFound() {
        DepartmentDto departmentDto = new DepartmentDto();
        departmentDto.setId(1L);
        when(departmentRepo.existsById(anyLong())).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> departmentService.putDepartment(departmentDto));
    }
}