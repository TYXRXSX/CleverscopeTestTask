package ru.clevertec.cleverscope.mapper;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.clevertec.cleverscope.dto.RequisiteDto;
import ru.clevertec.cleverscope.entity.Requisite;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class RequisiteMapperTest {

    @InjectMocks
    private RequisiteMapper requisiteMapper = RequisiteMapper.INSTANCE;

    @Test
    public void entityToDto_shouldMapEntityToDto() {
        Requisite requisite = new Requisite();
        requisite.setId(1L);
        requisite.setBankAccount("Requisite 1");

        RequisiteDto requisiteDto = requisiteMapper.entityToDto(requisite);

        assertEquals(requisite.getId(), requisiteDto.getId());
        assertEquals(requisite.getBankAccount(), requisiteDto.getBankAccount());
    }

    @Test
    public void dtoToEntity_shouldMapDtoToEntity() {
        RequisiteDto requisiteDto = new RequisiteDto();
        requisiteDto.setId(1L);
        requisiteDto.setBankAccount("Requisite 1");

        Requisite requisite = requisiteMapper.dtoToEntity(requisiteDto);

        assertEquals(requisiteDto.getId(), requisite.getId());
        assertEquals(requisiteDto.getBankAccount(), requisite.getBankAccount());
    }
}