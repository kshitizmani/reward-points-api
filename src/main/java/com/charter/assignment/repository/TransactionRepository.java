package com.charter.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.charter.assignment.entity.Transaction;

import java.time.LocalDate;
import java.util.List;

/**
 * Repository interface for {@link Transaction} entity.
 * Provides methods to perform database operations related to transactions.
 */
@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {

    /**
     * Retrieves a list of transactions for a given customer within a specified date range.
     *
     * @param customerId the ID of the customer.
     * @param startDate the start date of the period.
     * @param endDate the end date of the period.
     * @return a list of transactions matching the customer ID and date range.
     */
    List<Transaction> findByCustomerIdAndDateBetween(String customerId, LocalDate startDate, LocalDate endDate);
}