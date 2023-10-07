package com.kabgig.tgBot2.service;

import com.kabgig.tgBot2.repository.IncomeRepository;
import com.kabgig.tgBot2.repository.StatsRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
class StatsServiceTest {
    private StatsRepository statsRepository;

    @Autowired
    StatsServiceTest(StatsRepository statsRepository, IncomeRepository incomeRepository, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
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
    @Test
    void getCountOfSpendingsThatGreaterThanTest(){
        int i = statsRepository.getCountOfSpendingsThatGreaterThan(new BigDecimal(100));
        Assert.assertEquals(5,  i);
    }
}