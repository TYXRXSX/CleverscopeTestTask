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
import ru.clevertec.cleverscope.entity.Requisite;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@Testcontainers
class RequisiteRepoTest {
    @Container
    public static PostgreSQLContainer postgreSQLContainer = new PostgreSQLContainer("postgres:latest")
            .withDatabaseName("test")
            .withUsername("test")
            .withPassword("test");

    @Autowired
    private RequisiteRepo requisiteRepo;

    @BeforeEach
    public void setUp() {
        requisiteRepo.deleteAll();
    }

    @Test
    public void testSaveRequisite() {
        Requisite requisite = new Requisite();
        requisite.setBankAccount("Test Requisite");

        Requisite savedRequisite = requisiteRepo.save(requisite);

        assertNotNull(savedRequisite.getId());
        assertEquals("Test Requisite", savedRequisite.getBankAccount());
    }

    @Test
    public void testFindAllRequisites() {
        Requisite requisite1 = new Requisite();
        requisite1.setBankAccount("Requisite 1");
        requisiteRepo.save(requisite1);

        Requisite requisite2 = new Requisite();
        requisite2.setBankAccount("Requisite 2");
        requisiteRepo.save(requisite2);

        List<Requisite> requisites = requisiteRepo.findAll();

        assertEquals(2, requisites.size());
    }

    @Test
    public void testFindRequisiteByName() {
        Requisite requisite1 = new Requisite();
        requisite1.setBankAccount("Requisite 1");
        requisiteRepo.save(requisite1);

        Requisite requisite2 = new Requisite();
        requisite2.setBankAccount("Requisite 2");
        requisiteRepo.save(requisite2);

        Requisite foundRequisite = requisiteRepo.findAll().get(0);

        assertNotNull(foundRequisite);
        assertEquals("Requisite 1", foundRequisite.getBankAccount());
    }
}