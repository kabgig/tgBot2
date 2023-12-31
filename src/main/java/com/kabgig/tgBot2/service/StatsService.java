package com.kabgig.tgBot2.service;

import com.kabgig.tgBot2.repository.StatsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatsService {
    private final StatsRepository statsRepository;
    public int getCountOfIncomesThatGreater(BigDecimal amount){
        return statsRepository.getCountOfIncomesThatGreaterThan(amount);
    }

    public int getCountOfSpendingsThatGreater(BigDecimal amount) {
        return statsRepository.getCountOfSpendingsThatGreaterThan(amount);
    }

    public List<Integer> getFilteredIncomesAndSpendings(BigDecimal amount){
        return statsRepository.getIncomeSpendingList(amount);
    }
}
