package ru.clevertec.cleverscope.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.clevertec.cleverscope.dto.DepartmentDto;

public interface DepartmentService {

    Page<DepartmentDto> getAllDepartments(Pageable pageable);

    DepartmentDto saveDepartment(DepartmentDto departmentDto);

    void deleteDepartmentById(Long id);

    DepartmentDto getDepartmentById(Long id);

    DepartmentDto putDepartment(DepartmentDto departmentDto);

}
