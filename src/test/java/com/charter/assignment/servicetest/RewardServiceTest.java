package com.charter.assignment.servicetest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import com.charter.assignment.dto.RewardResponseDto;
import com.charter.assignment.entity.Transaction;
import com.charter.assignment.repository.TransactionRepository;
import com.charter.assignment.service.RewardService;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;




public class RewardServiceTest {

	private RewardService rewardService;
    private TransactionRepository transactionRepository;

    @BeforeEach
    void setUp() {
        transactionRepository = Mockito.mock(TransactionRepository.class);
        rewardService = new RewardService(transactionRepository);
    }

    @Test
    void testCalculateRewardPoints() {
        Transaction t1 = new Transaction(1L, "CUST1", 120, LocalDate.of(2025, 1, 10));
        Transaction t2 = new Transaction(2L, "CUST1", 80, LocalDate.of(2025, 2, 15));

        when(transactionRepository.findByCustomerIdAndDateBetween(
                Mockito.eq("CUST1"),
                Mockito.any(LocalDate.class),
                Mockito.any(LocalDate.class)
        )).thenReturn(List.of(t1, t2));

        RewardResponseDto result = rewardService.calculateRewardPoints("CUST1");

        assertEquals("CUST1", result.getCustomerId());
        assertEquals(90, result.getPointsPerMonth().get("2025-01"));
        assertEquals(30, result.getPointsPerMonth().get("2025-02"));
        assertEquals(120, result.getTotalPoints());
    }	
	
	
	
}
