package com.kabgig.tgBot2.service;

import com.kabgig.tgBot2.entity.Income;
import com.kabgig.tgBot2.repository.IncomeRepository;
import com.kabgig.tgBot2.repository.StatsRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
@ExtendWith(MockitoExtension.class)
class MockingStatsServiceTest {

    @InjectMocks
    private StatsRepository statsRepository;
    @Mock
    private IncomeRepository incomeRepository;

    @Test
    void getFilteredIncomesAndSpendings() {
        // Arrange
        final var savedIncome = Income.builder()
                .id(100001L)
                .income(new BigDecimal(10000))
                .chatId(123456789L)
                .creationDate(LocalDateTime.now())
                .build();

        when(incomeRepository.save(any(Income.class))).thenReturn(savedIncome);

        // Act
        final var actual = statsRepository.addIncome(savedIncome);

        // Assert
        assertThat(actual).usingRecursiveComparison().isEqualTo(savedIncome);
        verify(incomeRepository, times(1)).save(any(Income.class));
        verifyNoMoreInteractions(incomeRepository);

        //-----------
//        List<Integer> incomeSpendingList =
//                statsRepository.getIncomeSpendingList(new BigDecimal(20));
//        boolean isOkay = true;
//        for (int i : incomeSpendingList) {
//            if (i <= 20) isOkay = false;
//        }
//        Assert.assertTrue(isOkay);
    }

//    @Test
//    void getCountOfSpendingsThatGreaterThanTest() {
//        int i = statsRepository.getCountOfSpendingsThatGreaterThan(new BigDecimal(100));
//        Assert.assertEquals(5, i);
//    }
}