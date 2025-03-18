package com.charter.assignment.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.charter.assignment.entity.Transaction;
import com.charter.assignment.repository.TransactionRepository;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class RewardControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TransactionRepository transactionRepository;

    @BeforeEach
    void setUp() {
        transactionRepository.deleteAll();

        // Insert sample data for testing
        transactionRepository.save(new Transaction(null, "CUST1", 120.0, LocalDate.of(2025, 1, 15))); // 90 points
        transactionRepository.save(new Transaction(null, "CUST1", 80.0, LocalDate.of(2025, 2, 10)));   // 30 points
        transactionRepository.save(new Transaction(null, "CUST1", 50.0, LocalDate.of(2025, 3, 5)));    // 0 points
    }

    @Test
    void testGetRewardPoints_Success() throws Exception {
        mockMvc.perform(get("/api/rewards/CUST1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerId").value("CUST1"))
                .andExpect(jsonPath("$.pointsPerMonth.['2025-01']").value(90))
                .andExpect(jsonPath("$.pointsPerMonth.['2025-02']").value(30))
                .andExpect(jsonPath("$.pointsPerMonth.['2025-03']").value(0))
                .andExpect(jsonPath("$.totalPoints").value(120));
    }

    @Test
    void testGetRewardPoints_CustomerNotFound() throws Exception {
        mockMvc.perform(get("/api/rewards/CUST999")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // Should return an empty structure if no data is found
                .andExpect(jsonPath("$.customerId").value("CUST999"))
                .andExpect(jsonPath("$.pointsPerMonth").isEmpty())
                .andExpect(jsonPath("$.totalPoints").value(0));
    }
}
