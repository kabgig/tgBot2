package com.kabgig.tgBot2.repository;

import com.kabgig.tgBot2.entity.Income;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class StatsRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final IncomeRepository incomeRepository;

    public int getCountOfIncomesThatGreaterThan(BigDecimal amount){
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("amount", amount);
        return namedParameterJdbcTemplate.queryForObject(
                "SELECT count(*) FROM incomes WHERE income > :amount",
                parameters, new StatsRowMapper());
    }

    public int getCountOfSpendingsThatGreaterThan(BigDecimal amount) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("amountSpent", amount);
        return namedParameterJdbcTemplate.queryForObject(
                "SELECT count(*) FROM spend WHERE spend > :amountSpent",
                parameters, new StatsRowMapper());
    }

    public List<Integer> getIncomeSpendingList(BigDecimal amount){
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("amount", amount);
        List<Integer> incomeAndSpendList = new ArrayList<>();

        List<Integer> incomeList = namedParameterJdbcTemplate.queryForList(
                "SELECT income FROM incomes WHERE income > :amount",
                parameters,
                Integer.class);
        List<Integer> spendList = namedParameterJdbcTemplate.queryForList(
                "SELECT spend FROM spend WHERE spend > :amount",
                parameters,
                Integer.class);

        incomeAndSpendList.addAll(incomeList);
        incomeAndSpendList.addAll(spendList);
        return incomeAndSpendList;
    }

    private static final class StatsRowMapper implements RowMapper<Integer>{
        @Override
        public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
            return rs.getInt("COUNT(*)");
        }
    }

    public Income getIncomeById(Long id) {
        return incomeRepository.findById(id).get();
    }

    public Income addIncome(Income income){
        return incomeRepository.save(income);
    }



}
