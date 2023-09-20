package com.kabgig.tgBot2.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import com.kabgig.tgBot2.entity.Income;
import com.kabgig.tgBot2.entity.Spend;
import com.kabgig.tgBot2.repository.IncomeRepository;
import com.kabgig.tgBot2.repository.SpendRepository;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class FinanceService {

    public static final String ADD_INCOME = "/addincome";
    public static final String CURRENT_RATES = "/currentrates";
    public static final String ADD_SPEND = "/addspend";
    private final IncomeRepository incomeRepository;
    private final SpendRepository spendRepository;

    public String addFinanceOperation(String operationType, String price, Long chatId) {
        String message;
        if (ADD_INCOME.equalsIgnoreCase(operationType)) {
            Income income = new Income();
            income.setChatId(chatId);
            income.setIncome(new BigDecimal(price));
            incomeRepository.save(income);
            message = "Доход в размере " + price + " был успешно добавлен";
        } else {
            Spend spend = new Spend();
            spend.setChatId(chatId);
            spend.setSpend(new BigDecimal(price));
            spendRepository.save(spend);
            message = "Расход в размере " + price + " был успешно добавлен";
        }
        return message;
    }
}
