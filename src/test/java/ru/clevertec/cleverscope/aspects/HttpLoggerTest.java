package ru.clevertec.cleverscope.aspects;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import ru.clevertec.cleverscope.controller.DepartmentController;

@SpringBootTest
@AutoConfigureMockMvc
class HttpLoggerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DepartmentController departmentController;

}