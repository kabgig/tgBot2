package com.kabgig.tgBot2.repository;

import com.kabgig.tgBot2.entity.Income;
import com.kabgig.tgBot2.entity.Spend;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class SpendRepositoryTest {

    @Autowired
    private SpendRepository spendRepository;

    @Test
    public void testRepo() {
        for (int i = 0; i < 10; i++, spendRepository.save(new Spend()));
        final List<Spend> found = spendRepository.findAll();
        assertEquals(10, found.size());
    }


}
