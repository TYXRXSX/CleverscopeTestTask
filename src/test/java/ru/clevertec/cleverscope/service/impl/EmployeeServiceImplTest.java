package ru.clevertec.cleverscope.service.impl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import ru.clevertec.cleverscope.dto.EmployeeDto;
import ru.clevertec.cleverscope.entity.Employee;
import ru.clevertec.cleverscope.exception.ResourceNotFoundException;
import ru.clevertec.cleverscope.mapper.EmployeeMapper;
import ru.clevertec.cleverscope.repository.EmployeeRepo;
import ru.clevertec.cleverscope.service.impl.EmployeeServiceImpl;
import ru.clevertec.cleverscope.specification.EmployeeSpecification;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepo employeeRepo;

    @Mock
    private EmployeeMapper employeeMapper;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    public void saveEmployee_ReturnsEmployeeDto() {
        EmployeeDto employeeDto = mock(EmployeeDto.class);
        when(employeeMapper.dtoToEntity(employeeDto)).thenReturn(new Employee());
        when(employeeRepo.save(any(Employee.class))).thenReturn(new Employee());
        when(employeeMapper.entityToDto(any(Employee.class))).thenReturn(employeeDto);

        EmployeeDto result = employeeService.saveEmployee(employeeDto);

        assertEquals(employeeDto, result);
        verify(employeeRepo).save(any(Employee.class));
    }

    @Test
    public void deleteEmployeeById_DeletesEmployee_WhenEmployeeExists() {
        Long id = 1L;
        Employee employee = mock(Employee.class);
        when(employeeRepo.findById(id)).thenReturn(Optional.of(employee));

        employeeService.deleteEmployeeById(id);

        verify(employeeRepo).delete(employee);
    }

    @Test
    public void deleteEmployeeById_ThrowsResourceNotFoundException_WhenEmployeeNotFound() {
        Long id = 1L;
        when(employeeRepo.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> employeeService.deleteEmployeeById(id));
    }

    @Test
    public void getEmployeeById_ReturnsEmployeeDto_WhenEmployeeExists() {
        Long id = 1L;
        Employee employee = mock(Employee.class);
        when(employeeRepo.findById(id)).thenReturn(Optional.of(employee));

        EmployeeDto employeeDto = mock(EmployeeDto.class);
        when(employeeMapper.entityToDto(employee)).thenReturn(employeeDto);

        EmployeeDto result = employeeService.getEmployeeById(id);

        assertEquals(employeeDto, result);
    }

    @Test
    public void getEmployeeById_ThrowsResourceNotFoundException_WhenEmployeeNotFound() {
        Long id = 1L;
        when(employeeRepo.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> employeeService.getEmployeeById(id));
    }

    @Test
    public void putEmployee_ReturnsUpdatedEmployeeDto_WhenEmployeeExists() {
        EmployeeDto employeeDto = mock(EmployeeDto.class);
        Employee employee = mock(Employee.class);
        when(employeeMapper.dtoToEntity(employeeDto)).thenReturn(employee);

        Long id = 1L;
        when(employeeDto.getId()).thenReturn(id);
        when(employeeRepo.existsById(id)).thenReturn(true);

        Employee updatedEmployee = mock(Employee.class);
        when(employeeRepo.save(employee)).thenReturn(updatedEmployee);

        EmployeeDto updatedEmployeeDto = mock(EmployeeDto.class);
        when(employeeMapper.entityToDto(updatedEmployee)).thenReturn(updatedEmployeeDto);

        EmployeeDto result = employeeService.putEmployee(employeeDto);

        assertEquals(updatedEmployeeDto, result);
        verify(employeeRepo).save(employee);
    }

    @Test
    public void putEmployee_ThrowsResourceNotFoundException_WhenEmployeeNotFound() {
        EmployeeDto employeeDto = mock(EmployeeDto.class);
        when(employeeDto.getId()).thenReturn(1L);
        when(employeeRepo.existsById(anyLong())).thenReturn(false);

        assertThrows(ResourceNotFoundException.class, () -> employeeService.putEmployee(employeeDto));
    }
}