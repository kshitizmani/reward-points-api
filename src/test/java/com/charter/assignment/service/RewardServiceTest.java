package com.charter.assignment.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.charter.assignment.dto.RewardResponseDto;
import com.charter.assignment.entity.Transaction;
import com.charter.assignment.repository.TransactionRepository;

/**
 * Unit test class for {@link RewardService}.
 * <p>
 * This class tests the reward calculation logic based on customer transactions.
 */

@ExtendWith(MockitoExtension.class)
class RewardServiceTest {

    @Mock
    private TransactionRepository transactionRepository;

    @InjectMocks
    private RewardService rewardService;

    private List<Transaction> mockTransactions;

    /**
     * Sets up mock data before each test.
     */
    @BeforeEach
    void setUp() {
        mockTransactions = Arrays.asList(
                new Transaction(1L, "CUST1", 120.0, LocalDate.of(2025, 1, 10)),
                new Transaction(2L, "CUST1", 75.0, LocalDate.of(2025, 2, 15)),
                new Transaction(3L, "CUST1", 30.0, LocalDate.of(2025, 3, 5))
        );
    }
    
    /**
     * Tests the {@code calculateRewardPoints} method when transactions exist for the customer.
     * 
     * <p>Verifies that the reward points are calculated correctly for each month and in total.</p>
     */

    @Test
    void testCalculateRewardPoints_Success() {
        // Arrange
        LocalDate now = LocalDate.now();
        LocalDate threeMonthsAgo = now.minusMonths(3);
        when(transactionRepository.findByCustomerIdAndDateBetween("CUST1", threeMonthsAgo, now))
                .thenReturn(mockTransactions);

        // Act
        RewardResponseDto response = rewardService.calculateRewardPoints("CUST1");

        // Assert
        assertEquals("CUST1", response.getCustomerId());
        assertEquals(90, response.getPointsPerMonth().get("2025-01")); // 120 = 90 points
        assertEquals(25, response.getPointsPerMonth().get("2025-02")); // 75 = 25 points
        assertEquals(0, response.getPointsPerMonth().get("2025-03")); // 30 = 0 points
        assertEquals(115, response.getTotalPoints()); // Total points

        // Verify that the repository method was called once
        verify(transactionRepository, times(1)).findByCustomerIdAndDateBetween("CUST1", threeMonthsAgo, now);
    }
    
    /**
     * Tests the {@code calculateRewardPoints} method when no transactions exist for the customer.
     * 
     * <p>Verifies that the reward points and monthly breakdown are empty.</p>
     */

    @Test
    void testCalculateRewardPoints_NoTransactions() {
        // Arrange
        LocalDate now = LocalDate.now();
        LocalDate threeMonthsAgo = now.minusMonths(3);
        when(transactionRepository.findByCustomerIdAndDateBetween("CUST1", threeMonthsAgo, now))
                .thenReturn(List.of());

        // Act
        RewardResponseDto response = rewardService.calculateRewardPoints("CUST1");

        // Assert
        assertEquals("CUST1", response.getCustomerId());
        assertEquals(0, response.getTotalPoints());
        assertEquals(0, response.getPointsPerMonth().size());

        // Verify that the repository was called once
        verify(transactionRepository, times(1)).findByCustomerIdAndDateBetween("CUST1", threeMonthsAgo, now);
    }
}
