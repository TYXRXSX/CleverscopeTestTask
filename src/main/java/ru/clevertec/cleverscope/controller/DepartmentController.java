package ru.clevertec.cleverscope.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.cleverscope.dto.DepartmentDto;
import ru.clevertec.cleverscope.dto.EmployeeDto;
import ru.clevertec.cleverscope.service.DepartmentService;
import ru.clevertec.cleverscope.specification.EmployeeSpecification;

@RestController
@RequestMapping("api/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    @GetMapping
    public Page<DepartmentDto> getDepartments(@RequestParam(defaultValue = "0") int page,
                                                    @RequestParam(defaultValue = "10") int size) {
        return departmentService.getAllDepartments(PageRequest.of(page, size));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<DepartmentDto> getDepartmentById(@PathVariable("id") Long id){
        return ResponseEntity.ok(departmentService.getDepartmentById(id));
    }

    @PostMapping
    public ResponseEntity<DepartmentDto> postDepartment(@RequestBody @Valid DepartmentDto departmentDto){
        return ResponseEntity.ok(departmentService.saveDepartment(departmentDto));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HttpStatus> deleteDepartment(@PathVariable("id") Long id){
        departmentService.deleteDepartmentById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<DepartmentDto> putDepartment(@RequestBody @Valid DepartmentDto departmentDto){
        return ResponseEntity.ok(departmentService.putDepartment(departmentDto));
    }
}
