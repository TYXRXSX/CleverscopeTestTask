package ru.clevertec.cleverscope.controller;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.clevertec.cleverscope.dto.DepartmentDto;
import ru.clevertec.cleverscope.service.DepartmentService;

import java.util.Arrays;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@WireMockTest
public class DepartmentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentService departmentService;

    @BeforeEach
    public void setup() {
        WireMock.stubFor(get(urlPathEqualTo("/api/departments"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", MediaType.APPLICATION_JSON_VALUE)
                        .withBody("{\"content\":[{\"id\":1,\"name\":\"Department 1\"},{\"id\":2,\"name\":\"Department 2\"}],\"pageable\":{\"sort\":{\"unsorted\":true,\"sorted\":false,\"empty\":true},\"offset\":0,\"pageSize\":20,\"pageNumber\":0,\"paged\":true,\"unpaged\":false},\"totalElements\":2,\"totalPages\":1,\"last\":true,\"size\":20,\"number\":0,\"sort\":{\"unsorted\":true,\"sorted\":false,\"empty\":true},\"numberOfElements\":2,\"first\":true,\"empty\":false}")));
    }

    @Test
    public void getDepartments_shouldReturnListOfDepartments() throws Exception {
        Page<DepartmentDto> departmentPage = new PageImpl<>(Arrays.asList(DepartmentDto.builder().id(1L).name("Department 1").build(), DepartmentDto.builder().id(1L).name("Department 2").build()));
        when(departmentService.getAllDepartments(any(PageRequest.class))).thenReturn(departmentPage);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/departments"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.content[0].name").value("Department 1"));

        verify(departmentService).getAllDepartments(any(PageRequest.class));

    }

    @Test
    public void getDepartmentById_shouldReturnDepartment() throws Exception {
        DepartmentDto departmentDto = DepartmentDto.builder().id(1L).name("Department 1").build();
        when(departmentService.getDepartmentById(1L)).thenReturn(departmentDto);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/departments/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Department 1"));

        verify(departmentService).getDepartmentById(1L);
    }

    @Test
    public void createDepartment_shouldReturnCreatedDepartment() throws Exception {
        DepartmentDto departmentDto = DepartmentDto.builder().id(1L).name("New Department").build();
        when(departmentService.saveDepartment(any(DepartmentDto.class))).thenReturn(departmentDto);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/departments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"New Department\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("New Department"));

        verify(departmentService).saveDepartment(any(DepartmentDto.class));
    }

    @Test
    public void updateDepartment_shouldReturnUpdatedDepartment() throws Exception {
        DepartmentDto departmentDto = DepartmentDto.builder().id(1L).name("Updated Department").build();
        when(departmentService.putDepartment(any(DepartmentDto.class))).thenReturn(departmentDto);

        mockMvc.perform(MockMvcRequestBuilders.put("/api/departments")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\":1,\"name\":\"Updated Department\"}"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Updated Department"));


        verify(departmentService).putDepartment(any(DepartmentDto.class));

    }

    @Test
    public void deleteDepartment_shouldReturnNoContent() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/api/departments/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        verify(departmentService).deleteDepartmentById(1L);
    }

}