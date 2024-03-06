package ru.clevertec.cleverscope.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import ru.clevertec.cleverscope.entity.Employee;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Testcontainers
class EmployeeRepoTest {
    @Container
    public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:latest")
            .withDatabaseName("test")
            .withUsername("test")
            .withPassword("test");

    @Autowired
    private EmployeeRepo employeeRepo;

    @BeforeEach
    public void setUp() {
        employeeRepo.deleteAll();
    }

    @Test
    public void testSaveEmployee() {
        // Given
        Employee employee = new Employee();
        employee.setName("Test Employee");

        // When
        Employee savedEmployee = employeeRepo.save(employee);

        // Then
        assertNotNull(savedEmployee.getId());
        assertEquals("Test Employee", savedEmployee.getName());
    }

    @Test
    public void testFindAllEmployees() {
        // Given
        Employee employee1 = new Employee();
        employee1.setName("Employee 1");
        employeeRepo.save(employee1);

        Employee employee2 = new Employee();
        employee2.setName("Employee 2");
        employeeRepo.save(employee2);

        // When
        List<Employee> employees = employeeRepo.findAll();

        // Then
        assertEquals(2, employees.size());
    }

    @Test
    public void testFindEmployeeByName() {
        // Given
        Employee employee1 = new Employee();
        employee1.setName("Employee 1");
        employeeRepo.save(employee1);

        Employee employee2 = new Employee();
        employee2.setName("Employee 2");
        employeeRepo.save(employee2);

        // When
        Employee foundEmployee = employeeRepo.findAll().get(0);

        // Then
        assertNotNull(foundEmployee);
        assertEquals("Employee 1", foundEmployee.getName());
    }
}