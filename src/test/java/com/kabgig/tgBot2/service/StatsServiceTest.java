package com.kabgig.tgBot2.service;

import com.kabgig.tgBot2.repository.StatsRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
@ActiveProfiles("test")
@Sql(scripts = "classpath:data-test.sql", executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(scripts = "classpath:data-after-tests.sql", executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class StatsServiceTest {

    private final StatsRepository statsRepository;

    @Autowired
    StatsServiceTest(StatsRepository statsRepository) {
        this.statsRepository = statsRepository;
    }

    @Test
    void getFilteredIncomesAndSpendings() {
        List<Integer> incomeSpendingList =
                statsRepository.getIncomeSpendingList(new BigDecimal(20));
        boolean isOkay = true;
        for(int i: incomeSpendingList){
            if (i <= 20) isOkay = false;
        }
        Assert.assertTrue(isOkay);

    }
}