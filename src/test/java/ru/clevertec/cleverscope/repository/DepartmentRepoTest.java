package ru.clevertec.cleverscope.repository;

import org.junit.Before;
import org.junit.ClassRule;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.clevertec.cleverscope.entity.Department;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Testcontainers
public class DepartmentRepoTest {
    @Container
    public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:latest")
            .withDatabaseName("test")
            .withUsername("test")
            .withPassword("test");

    @Autowired
    private DepartmentRepo departmentRepo;

    @BeforeEach
    public void setUp() {
        departmentRepo.deleteAll();
    }

    @Test
    public void testSaveDepartment() {
        Department department = new Department();
        department.setName("Test Department");

        Department savedDepartment = departmentRepo.save(department);

        assertNotNull(savedDepartment.getId());
        assertEquals("Test Department", savedDepartment.getName());
    }

    @Test
    public void testFindAllDepartments() {
        Department department1 = new Department();
        department1.setName("Department 1");
        departmentRepo.save(department1);

        Department department2 = new Department();
        department2.setName("Department 2");
        departmentRepo.save(department2);

        List<Department> departments = departmentRepo.findAll();

        assertEquals(2, departments.size());
    }

    @Test
    public void testFindDepartmentByName() {
        Department department1 = new Department();
        department1.setName("Department 1");
        departmentRepo.save(department1);

        Department department2 = new Department();
        department2.setName("Department 2");
        departmentRepo.save(department2);

        Department foundDepartment = departmentRepo.findAll().get(0);

        assertNotNull(foundDepartment);
        assertEquals("Department 1", foundDepartment.getName());
    }
}