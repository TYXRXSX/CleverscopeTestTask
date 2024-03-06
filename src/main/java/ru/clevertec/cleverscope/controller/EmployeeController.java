package ru.clevertec.cleverscope.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.clevertec.cleverscope.dto.EmployeeDto;
import ru.clevertec.cleverscope.service.EmployeeService;
import ru.clevertec.cleverscope.specification.EmployeeSpecification;

@RestController
@RequestMapping("api/employees")
@RequiredArgsConstructor
public class EmployeeController {

    private final EmployeeService employeeService;

    @GetMapping
    public Page<EmployeeDto> getFilteredEmployees(@RequestParam(required = false) Long departmentId,
                                                  @RequestParam(required = false) String employeeName,
                                                  @RequestParam(required = false) String city,
                                                  @RequestParam(defaultValue = "") String sort,
                                                  @RequestParam(defaultValue = "0") int page,
                                                  @RequestParam(defaultValue = "10") int size) {
        return employeeService.getAllEmployees(new EmployeeSpecification(departmentId, employeeName, city, sort), page, size);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<EmployeeDto> getEmployeeById(@PathVariable("id") Long id){
        return ResponseEntity.ok(employeeService.getEmployeeById(id));
    }

    @PostMapping
    public ResponseEntity<EmployeeDto> postEmployee(@RequestBody @Valid EmployeeDto employeeDto){
        return ResponseEntity.ok(employeeService.saveEmployee(employeeDto));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable("id") Long id){
        employeeService.deleteEmployeeById(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<EmployeeDto> putEmployee(@RequestBody @Valid EmployeeDto employeeDto){
        return ResponseEntity.ok(employeeService.putEmployee(employeeDto));
    }
}
