package ru.clevertec.cleverscope.dto;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.clevertec.cleverscope.entity.Department;
import ru.clevertec.cleverscope.entity.Requisite;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String city;

    @NotBlank
    private String email;

    @Valid
    private DepartmentDto department;

    @Valid
    private RequisiteDto requisite;

}
