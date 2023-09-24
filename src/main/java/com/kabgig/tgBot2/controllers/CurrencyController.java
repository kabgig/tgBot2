package com.kabgig.tgBot2.controllers;

import com.kabgig.tgBot2.dto.ValuteCursOnDate;
import com.kabgig.tgBot2.service.CentralRussianBankService;
import com.kabgig.tgBot2.service.StatsService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class CurrencyController {

    private final CentralRussianBankService centralRussianBankService;
    private final StatsService statsService;

    @GetMapping("/getCurrencies")
    public List<ValuteCursOnDate> getValuteCursOnDate() throws Exception {
        return centralRussianBankService.getCurrenciesFromCbr();
    }
    @GetMapping("/getStats")
    @ApiOperation(value = "Получение количества пополнений, которые превышают определенную сумму")
    public int getStatsAboutIncomesthatGreater(@RequestParam(value="amount")BigDecimal amount){
        return statsService.getCountOfIncomesThatGreater(amount);
    }

    @GetMapping("/getStatsSpend")
    @ApiOperation(value = "Получение количества трат, которые превышают определенную сумму")
    public int getStatsAboutSpendingsThatGreater(@RequestParam(value="amount")BigDecimal amount){
        return statsService.getCountOfSpendingsThatGreater(amount);
    }
}
