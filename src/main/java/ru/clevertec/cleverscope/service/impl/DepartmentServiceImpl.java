package ru.clevertec.cleverscope.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.clevertec.cleverscope.dto.DepartmentDto;
import ru.clevertec.cleverscope.entity.Department;
import ru.clevertec.cleverscope.exception.ResourceNotFoundException;
import ru.clevertec.cleverscope.mapper.DepartmentMapper;
import ru.clevertec.cleverscope.repository.DepartmentRepo;
import ru.clevertec.cleverscope.service.DepartmentService;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepo departmentRepo;
    private final DepartmentMapper departmentMapper;

    @Override
    public Page<DepartmentDto> getAllDepartments(Pageable pageable) {
        return departmentRepo.findAll(pageable).map(departmentMapper::entityToDto);
    }

    @Override
    public DepartmentDto saveDepartment(DepartmentDto departmentDto) {
        return departmentMapper.entityToDto(departmentRepo.save(departmentMapper.dtoToEntity(departmentDto)));
    }

    @Override
    public void deleteDepartmentById(Long id) {
        Department department = departmentRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Department not found to delete by id: %s".formatted(id)));
        department.setDeletedDate(LocalDate.now());
        departmentRepo.save(department);
    }

    @Override
    public DepartmentDto getDepartmentById(Long id) {
        return departmentMapper.entityToDto(departmentRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Department not found by id: %s".formatted(id))));
    }

    @Override
    public DepartmentDto putDepartment(DepartmentDto departmentDto) {
        Department department = departmentMapper.dtoToEntity(departmentDto);
        if (!departmentRepo.existsById(departmentDto.getId())){
            throw new ResourceNotFoundException("Department not found by id: %s".formatted(departmentDto.getId()));
        }
        return departmentMapper.entityToDto(departmentRepo.save(department));
    }
}
