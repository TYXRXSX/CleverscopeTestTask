package ru.clevertec.cleverscope.service.impl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import ru.clevertec.cleverscope.dto.RequisiteDto;
import ru.clevertec.cleverscope.entity.Requisite;
import ru.clevertec.cleverscope.exception.ResourceNotFoundException;
import ru.clevertec.cleverscope.mapper.RequisiteMapper;
import ru.clevertec.cleverscope.repository.RequisiteRepo;
import ru.clevertec.cleverscope.service.impl.RequisiteServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
public class RequisiteServiceImplTest {

    @Mock
    private RequisiteRepo requisiteRepo;

    @Mock
    private RequisiteMapper requisiteMapper;

    @InjectMocks
    private RequisiteServiceImpl requisiteService;

    @Test
    public void saveRequisite_ReturnsRequisiteDto() {
        RequisiteDto requisiteDto = mock(RequisiteDto.class);
        Requisite requisite = mock(Requisite.class);
        when(requisiteMapper.dtoToEntity(requisiteDto)).thenReturn(requisite);
        when(requisiteRepo.save(requisite)).thenReturn(requisite);
        when(requisiteMapper.entityToDto(requisite)).thenReturn(requisiteDto);

        RequisiteDto result = requisiteService.saveRequisite(requisiteDto);

        assertEquals(requisiteDto, result);
        verify(requisiteRepo).save(requisite);
    }

    @Test
    public void deleteRequisiteById_SetsDeletedDate_WhenRequisiteExists() {
        Long id = 1L;
        Requisite requisite = mock(Requisite.class);
        when(requisiteRepo.findById(id)).thenReturn(Optional.of(requisite));

        requisiteService.deleteRequisiteById(id);

        verify(requisite).setDeletedDate(LocalDate.now());
        verify(requisiteRepo).save(requisite);
    }

    @Test
    public void deleteRequisiteById_ThrowsResourceNotFoundException_WhenRequisiteNotFound() {
        Long id = 1L;
        when(requisiteRepo.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> requisiteService.deleteRequisiteById(id));
    }

    @Test
    public void getRequisiteById_ReturnsRequisiteDto_WhenRequisiteExists() {
        Long id = 1L;
        Requisite requisite = mock(Requisite.class);
        when(requisiteRepo.findById(id)).thenReturn(Optional.of(requisite));

        RequisiteDto requisiteDto = mock(RequisiteDto.class);
        when(requisiteMapper.entityToDto(requisite)).thenReturn(requisiteDto);

        RequisiteDto result = requisiteService.getRequisiteById(id);

        assertEquals(requisiteDto, result);
    }

    @Test
    public void getRequisiteById_ThrowsResourceNotFoundException_WhenRequisiteNotFound() {
        Long id = 1L;
        when(requisiteRepo.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> requisiteService.getRequisiteById(id));
    }

    @Test
    public void putRequisite_ReturnsUpdatedRequisiteDto() {
        RequisiteDto requisiteDto = mock(RequisiteDto.class);
        Requisite requisite = mock(Requisite.class);
        when(requisiteMapper.dtoToEntity(requisiteDto)).thenReturn(requisite);

        Requisite updatedRequisite = mock(Requisite.class);
        when(requisiteRepo.save(requisite)).thenReturn(updatedRequisite);

        RequisiteDto updatedRequisiteDto = mock(RequisiteDto.class);
        when(requisiteMapper.dtoToEntity(updatedRequisiteDto)).thenReturn(updatedRequisite);
        when(requisiteMapper.entityToDto(updatedRequisite)).thenReturn(updatedRequisiteDto);

        RequisiteDto result = requisiteService.putRequisite(requisiteDto);

        assertEquals(updatedRequisiteDto, result);
        verify(requisiteRepo).save(requisite);
    }
}