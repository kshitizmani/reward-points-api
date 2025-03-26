package com.charter.assignment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.charter.assignment.controller.RewardController;
import com.charter.assignment.dto.RewardResponse;
import com.charter.assignment.service.RewardService;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration test class for {@link RewardController}. Tests the endpoints to
 * ensure they function correctly with the {@link RewardService}.
 */

@ExtendWith(MockitoExtension.class)
public class IntegrationTest {

	private MockMvc mockMvc;

	@Mock
	private RewardService rewardService;

	@InjectMocks
	private RewardController rewardController;

	private RewardResponse mockResponse;

	/**
	 * Sets up the test environment before each test. Initializes {@code MockMvc}
	 * and prepares sample mock data.
	 */

	@BeforeEach
	void setUp() {
		// Initialize MockMvc with the RewardController
		mockMvc = MockMvcBuilders.standaloneSetup(rewardController).build();

		// Sample mock data
		Map<String, Integer> pointsPerMonth = new HashMap<>();
		pointsPerMonth.put("2025-01", 90);
		pointsPerMonth.put("2025-02", 25);

		mockResponse = new RewardResponse("CUST1", pointsPerMonth, 115);
	}

	/**
	 * Tests the {@code getRewardPoints} endpoint for a successful response.
	 * 
	 * <p>
	 * Verifies that the response contains the correct reward points details.
	 * </p>
	 *
	 */

	@Test
	void testGetRewardPoints_Success() throws Exception {
		// Arrange
		when(rewardService.calculateRewardPoints("CUST1")).thenReturn(mockResponse);

		// Act & Assert
		mockMvc.perform(get("/customers/CUST1").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
				.andExpect(jsonPath("$.customerId").value("CUST1"))
				.andExpect(jsonPath("$.pointsPerMonth['2025-01']").value(90))
				.andExpect(jsonPath("$.pointsPerMonth['2025-02']").value(25))
				.andExpect(jsonPath("$.totalPoints").value(115));
	}

	/**
	 * Tests the {@code getRewardPoints} endpoint when the customer is not found.
	 * 
	 * <p>
	 * Verifies that the response is empty when the customer does not exist.
	 * </p>
	 */

	@Test
	void testGetRewardPoints_CustomerNotFound() throws Exception {
		// Arrange
		when(rewardService.calculateRewardPoints("CUST2")).thenReturn(null);

		// Act & Assert
		mockMvc.perform(get("/customers/CUST2").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()) // Status
																													// OK
																													// but
																													// empty
																													// response
				.andExpect(content().string(""));
	}

}
