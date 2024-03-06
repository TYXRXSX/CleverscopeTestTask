package ru.clevertec.cleverscope.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.clevertec.cleverscope.dto.EmployeeDto;
import ru.clevertec.cleverscope.service.EmployeeService;
import ru.clevertec.cleverscope.specification.EmployeeSpecification;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    void getEmployeeById_ReturnsEmployeeDto() {
        Long id = 1L;
        EmployeeDto expectedDto = mock(EmployeeDto.class);
        when(employeeService.getEmployeeById(id)).thenReturn(expectedDto);

        ResponseEntity<EmployeeDto> response = employeeController.getEmployeeById(id);

        assertEquals(ResponseEntity.ok(expectedDto), response);
        verify(employeeService, times(1)).getEmployeeById(id);
    }

    @Test
    void postEmployee_ReturnsEmployeeDto() {
        EmployeeDto employeeDto = mock(EmployeeDto.class);
        EmployeeDto expectedDto = mock(EmployeeDto.class);
        when(employeeService.saveEmployee(employeeDto)).thenReturn(expectedDto);

        ResponseEntity<EmployeeDto> response = employeeController.postEmployee(employeeDto);

        assertEquals(ResponseEntity.ok(expectedDto), response);
        verify(employeeService, times(1)).saveEmployee(employeeDto);
    }

    @Test
    void deleteEmployee_ReturnsHttpStatusOk() {
        Long id = 1L;

        ResponseEntity<HttpStatus> response = employeeController.deleteEmployee(id);

        assertEquals(ResponseEntity.ok(HttpStatus.OK), response);
        verify(employeeService, times(1)).deleteEmployeeById(id);
    }

    @Test
    void putEmployee_ReturnsEmployeeDto() {
        EmployeeDto employeeDto = mock(EmployeeDto.class);
        EmployeeDto expectedDto = mock(EmployeeDto.class);
        when(employeeService.putEmployee(employeeDto)).thenReturn(expectedDto);

        ResponseEntity<EmployeeDto> response = employeeController.putEmployee(employeeDto);

        assertEquals(ResponseEntity.ok(expectedDto), response);
        verify(employeeService, times(1)).putEmployee(employeeDto);
    }
}