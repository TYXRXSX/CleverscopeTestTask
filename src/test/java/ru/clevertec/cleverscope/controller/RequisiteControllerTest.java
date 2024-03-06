package ru.clevertec.cleverscope.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.clevertec.cleverscope.dto.RequisiteDto;
import ru.clevertec.cleverscope.service.RequisiteService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class RequisiteControllerTest {

    @Mock
    private RequisiteService requisiteService;

    @InjectMocks
    private RequisiteController requisiteController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getRequisites_ReturnsPageOfRequisiteDto() {
        int page = 0;
        int size = 10;
        Page<RequisiteDto> expectedPage = mock(Page.class);
        when(requisiteService.getAllRequisites(PageRequest.of(page, size))).thenReturn(expectedPage);

        // Act
        Page<RequisiteDto> actualPage = requisiteController.getRequisites(page, size);

        // Assert
        assertEquals(expectedPage, actualPage);
        verify(requisiteService, times(1)).getAllRequisites(PageRequest.of(page, size));
    }

    @Test
    void getRequisiteById_ReturnsRequisiteDto() {
        Long id = 1L;
        RequisiteDto expectedDto = mock(RequisiteDto.class);
        when(requisiteService.getRequisiteById(id)).thenReturn(expectedDto);

        ResponseEntity<RequisiteDto> response = requisiteController.getRequisiteById(id);

        assertEquals(ResponseEntity.ok(expectedDto), response);
        verify(requisiteService, times(1)).getRequisiteById(id);
    }

    @Test
    void postRequisite_ReturnsRequisiteDto() {
        RequisiteDto requisiteDto = mock(RequisiteDto.class);
        RequisiteDto expectedDto = mock(RequisiteDto.class);
        when(requisiteService.saveRequisite(requisiteDto)).thenReturn(expectedDto);

        ResponseEntity<RequisiteDto> response = requisiteController.postRequisite(requisiteDto);

        assertEquals(ResponseEntity.ok(expectedDto), response);
        verify(requisiteService, times(1)).saveRequisite(requisiteDto);
    }

    @Test
    void deleteRequisite_ReturnsHttpStatusOk() {
        Long id = 1L;

        ResponseEntity<HttpStatus> response = requisiteController.deleteRequisite(id);

        assertEquals(ResponseEntity.ok(HttpStatus.OK), response);
        verify(requisiteService, times(1)).deleteRequisiteById(id);
    }

    @Test
    void putRequisite_ReturnsRequisiteDto() {
        RequisiteDto requisiteDto = mock(RequisiteDto.class);
        RequisiteDto expectedDto = mock(RequisiteDto.class);
        when(requisiteService.putRequisite(requisiteDto)).thenReturn(expectedDto);

        ResponseEntity<RequisiteDto> response = requisiteController.putRequisite(requisiteDto);

        assertEquals(ResponseEntity.ok(expectedDto), response);
        verify(requisiteService, times(1)).putRequisite(requisiteDto);
    }
}