package ru.clevertec.cleverscope.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Requisite {
    @Id
    private Long id;

    private String bankAccount;

    @Column(name = "deleted_date")
    private LocalDate deletedDate;
}
