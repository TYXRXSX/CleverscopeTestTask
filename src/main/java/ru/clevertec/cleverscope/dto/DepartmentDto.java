package ru.clevertec.cleverscope.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentDto {

    private Long id;

    @NotBlank
    private String name;

    private LocalDate deletedDate;

}
