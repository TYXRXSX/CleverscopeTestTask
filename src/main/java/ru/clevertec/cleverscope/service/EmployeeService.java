package ru.clevertec.cleverscope.service;

import org.springframework.data.domain.Page;
import ru.clevertec.cleverscope.dto.EmployeeDto;
import ru.clevertec.cleverscope.specification.EmployeeSpecification;

public interface EmployeeService {

    Page<EmployeeDto> getAllEmployees(EmployeeSpecification employeeSpecification, int page, int size);

    EmployeeDto saveEmployee(EmployeeDto employeeDto);

    void deleteEmployeeById(Long id);

    EmployeeDto getEmployeeById(Long id);

    EmployeeDto putEmployee(EmployeeDto employeeDto);

}
