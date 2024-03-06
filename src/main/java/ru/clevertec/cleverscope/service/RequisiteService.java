package ru.clevertec.cleverscope.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ru.clevertec.cleverscope.dto.DepartmentDto;
import ru.clevertec.cleverscope.dto.RequisiteDto;

public interface RequisiteService {
    Page<RequisiteDto> getAllRequisites(Pageable pageable);

    RequisiteDto saveRequisite(RequisiteDto requisiteDto);

    void deleteRequisiteById(Long id);

    RequisiteDto getRequisiteById(Long id);

    RequisiteDto putRequisite(RequisiteDto requisiteDto);
}
