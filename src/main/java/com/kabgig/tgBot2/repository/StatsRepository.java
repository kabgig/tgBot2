package com.kabgig.tgBot2.repository;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

@Repository
@RequiredArgsConstructor
public class StatsRepository {
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

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

    private static final class StatsRowMapper implements RowMapper<Integer>{
        @Override
        public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
            return rs.getInt("COUNT(*)");
        }
    }

}
