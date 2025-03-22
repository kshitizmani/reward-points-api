package com.charter.assignment.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

/**
 * Entity class representing a transaction made by a customer.
 * Stores information about the transaction amount, date, and associated customer.
 */

@Entity
public class Transaction {
	
	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    private String customerId;
	    private double amount;
	    private LocalDate date;
	    
	    /**
	     * Constructs a new {@code Transaction} with the specified details.
	     *
	     * @param id the unique ID of the transaction.
	     * @param customerId the ID of the customer who made the transaction.
	     * @param amount the amount of the transaction.
	     * @param date the date when the transaction occurred.
	     */
		public Transaction(Long id, String customerId, double amount, LocalDate date) {
			super();
			this.id = id;
			this.customerId = customerId;
			this.amount = amount;
			this.date = date;
		}
		
		 /**
	     * Default constructor.
	     */
		
		public Transaction() {
			super();

		}
		
	
		/**
	     * getter and setter methods
	     *
	     */	

		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getCustomerId() {
			return customerId;
		}
		public void setCustomerId(String customerId) {
			this.customerId = customerId;
		}
		public double getAmount() {
			return amount;
		}
		public void setAmount(double amount) {
			this.amount = amount;
		}
		public LocalDate getDate() {
			return date;
		}
		public void setDate(LocalDate date) {
			this.date = date;
		}
	    
	    
	    
	    
	    

}
