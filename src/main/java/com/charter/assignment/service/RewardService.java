package com.charter.assignment.service;

import org.springframework.stereotype.Service;

import com.charter.assignment.dto.RewardResponseDto;
import com.charter.assignment.entity.Transaction;
import com.charter.assignment.repository.TransactionRepository;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Service class responsible for calculating reward points based on customer transactions.
 */

@Service
public class RewardService {
	
	private final TransactionRepository transactionRepository;

    public RewardService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    
    /**
     * Computes the reward points for each month and the total points for a given customer.
     * 
     * <p>The calculation is based on transactions made within the last 3 months.
     * Reward points are calculated as follows:
     * <ul>
     *   <li>1 point for every dollar spent over $50.</li>
     *   <li>2 points for every dollar spent over $100.</li>
     * </ul>
     *
     * @param customerId the ID of the customer.
     * @return a {@code RewardResponseDto} containing monthly and total reward points.
     */
    
    public RewardResponseDto calculateRewardPoints(String customerId) {
        LocalDate now = LocalDate.now();
        LocalDate threeMonthsAgo = now.minusMonths(3);

        List<Transaction> transactions = transactionRepository
                .findByCustomerIdAndDateBetween(customerId, threeMonthsAgo, now);

        Map<String, Integer> monthlyPoints = new HashMap<>();
        int totalPoints = 0;

        for (Transaction transaction : transactions) {
            int points = calculatePoints(transaction.getAmount());
            totalPoints += points;

            String month = YearMonth.from(transaction.getDate()).toString();
            monthlyPoints.put(month, monthlyPoints.getOrDefault(month, 0) + points);
        }

        return new RewardResponseDto(customerId, monthlyPoints, totalPoints);
    }
    
    /**
     * Calculates the reward points based on the transaction amount.
     * 
     * <p>Calculation rules:
     * <ul>
     *   <li>1 point for every dollar spent over $50.</li>
     *   <li>2 points for every dollar spent over $100.</li>
     * </ul>
     *
     * @param amount the transaction amount.
     * @return the calculated reward points.
     */
    
    private int calculatePoints(double amount) {
        int points = 0;
        if (amount > 100) {
            points += (int) ((amount - 100) * 2);
            amount = 100;
        }
        if (amount > 50) {
            points += (int) ((amount - 50) * 1);
        }
        return points;
    }

}
