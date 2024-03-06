package ru.clevertec.cleverscope.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.clevertec.cleverscope.dto.EmployeeDto;
import ru.clevertec.cleverscope.exception.ResourceNotFoundException;
import ru.clevertec.cleverscope.mapper.EmployeeMapper;
import ru.clevertec.cleverscope.repository.EmployeeRepo;
import ru.clevertec.cleverscope.service.EmployeeService;
import ru.clevertec.cleverscope.specification.EmployeeSpecification;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepo employeeRepo;
    private final EmployeeMapper employeeMapper;
    @Override
    public Page<EmployeeDto> getAllEmployees(EmployeeSpecification employeeSpecification, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return employeeRepo.findAll(employeeSpecification, pageable).map(employeeMapper::entityToDto);
    }

    @Override
    public EmployeeDto saveEmployee(EmployeeDto employeeDto) {
        return employeeMapper.entityToDto(employeeRepo.save(employeeMapper.dtoToEntity(employeeDto)));
    }

    @Override
    public void deleteEmployeeById(Long id) {
        var employee = employeeRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found to delete by id: %s".formatted(id)));
        employeeRepo.delete(employee);
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
        return employeeMapper.entityToDto(employeeRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Employee not found by id: %s".formatted(id))));
    }

    @Override
    public EmployeeDto putEmployee(EmployeeDto employeeDto) {
        if (!employeeRepo.existsById(employeeDto.getId())){
            throw new ResourceNotFoundException("Employee not found by id: %s".formatted(employeeDto.getId()));
        }

        return employeeMapper.entityToDto(employeeRepo.save(employeeMapper.dtoToEntity(employeeDto)));
    }
}
