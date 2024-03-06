package ru.clevertec.cleverscope.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.clevertec.cleverscope.entity.Employee;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RequisiteDto {

    private Long id;

    @NotBlank
    private String bankAccount;

    private LocalDate deletedDate;
}
