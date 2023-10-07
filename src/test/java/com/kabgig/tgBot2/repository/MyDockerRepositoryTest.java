package com.kabgig.tgBot2.repository;

import com.kabgig.tgBot2.entity.Income;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ConfigurableApplicationContext;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@Testcontainers
@Transactional
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MyDockerRepositoryTest {

    @Container
    public static MySQLContainer<?> mysqlContainer = new MySQLContainer<>(DockerImageName.parse("mysql:5.7"))
            .withDatabaseName("testdb")
            .withUsername("root")
            .withPassword("root_password");

    @BeforeAll
    public static void setUp(@Autowired ConfigurableApplicationContext context) {
       // mysqlContainer.start();

        // Configure your Spring application to use the MySQL container
        String jdbcUrl = mysqlContainer.getJdbcUrl();
        String username = mysqlContainer.getUsername();
        String password = mysqlContainer.getPassword();

        // Configure your Spring application's data source properties programmatically
        TestPropertyValues.of(
                "spring.datasource.url=" + jdbcUrl,
                "spring.datasource.username=" + username,
                "spring.datasource.password=" + password
        ).applyTo(context.getEnvironment());
    }

    @Autowired
    private IncomeRepository incomeRepository;

    @Test
    public void testSaveAndRetrieveIncome() {
        // Create and save an Income entity
        Income income = Income.builder()
                .chatId(123L)
                .income(BigDecimal.valueOf(100.0))
                .creationDate(LocalDateTime.now())
                .build();
        incomeRepository.save(income);

        // Retrieve the saved entity
        Income savedIncome = incomeRepository.findById(income.getId()).orElse(null);

        // Assert that the saved entity is not null and has the expected values
        assertNotNull(savedIncome);
        assertEquals(123L, savedIncome.getChatId());
        assertEquals(BigDecimal.valueOf(100.0), savedIncome.getIncome());
    }
}
