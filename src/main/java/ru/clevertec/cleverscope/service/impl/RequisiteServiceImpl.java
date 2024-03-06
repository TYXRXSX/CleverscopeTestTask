package ru.clevertec.cleverscope.service.impl;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import ru.clevertec.cleverscope.dto.RequisiteDto;
import ru.clevertec.cleverscope.entity.Department;
import ru.clevertec.cleverscope.entity.Requisite;
import ru.clevertec.cleverscope.exception.ResourceNotFoundException;
import ru.clevertec.cleverscope.mapper.RequisiteMapper;
import ru.clevertec.cleverscope.repository.RequisiteRepo;
import ru.clevertec.cleverscope.service.RequisiteService;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class RequisiteServiceImpl implements RequisiteService {

    private final RequisiteRepo requisiteRepo;
    private final RequisiteMapper requisiteMapper;

    @Override
    public Page<RequisiteDto> getAllRequisites(Pageable pageable) {
        return requisiteRepo.findAll(pageable).map(requisiteMapper::entityToDto);
    }

    @Override
    public RequisiteDto saveRequisite(RequisiteDto requisiteDto) {
        return requisiteMapper.entityToDto(requisiteRepo.save(requisiteMapper.dtoToEntity(requisiteDto)));
    }

    @Override
    public void deleteRequisiteById(Long id) {
        Requisite requisite = requisiteRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Requisite not found to delete by id: %s".formatted(id)));
        requisite.setDeletedDate(LocalDate.now());
        requisiteRepo.save(requisite);
    }

    @Override
    public RequisiteDto getRequisiteById(Long id) {
        return requisiteMapper.entityToDto(requisiteRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Requisite not found by id: %s".formatted(id))));
    }

    @Override
    public RequisiteDto putRequisite(RequisiteDto requisiteDto) {
        return requisiteMapper.entityToDto(requisiteRepo.save(requisiteMapper.dtoToEntity(requisiteDto)));
    }
}
