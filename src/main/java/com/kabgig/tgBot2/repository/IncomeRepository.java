package com.kabgig.tgBot2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.kabgig.tgBot2.entity.Income;

@Repository
public interface IncomeRepository extends JpaRepository<Income, Long> {
}
