package com.charter.assignment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.charter.assignment.entity.Transaction;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
	
	//Retrieves a list of transactions for a given customer.
	
    List<Transaction> findByCustomerIdAndDateBetween(String customerId, LocalDate startDate, LocalDate endDate);
}