package com.charter.assignment.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.charter.assignment.dto.RewardResponseDto;
import com.charter.assignment.service.RewardService;

/**
 * REST controller for handling reward points calculation for customers.
 * Provides an endpoint to retrieve the reward points earned by a customer for
 * each month and the total points.
 */

@RestController
@RequestMapping("/customers")
public class RewardController {

	private final RewardService rewardService;

	public RewardController(RewardService rewardService) {
		this.rewardService = rewardService;
	}

	/**
	 * Retrieves the reward points earned by a customer for each month and the total
	 * points.
	 *
	 * @param customerId the ID of the customer.
	 * @return a {@code RewardResponseDto} containing the reward points details.
	 */

	@GetMapping("/{customerId}")
	public RewardResponseDto getRewardPoints(@PathVariable String customerId) {
		return rewardService.calculateRewardPoints(customerId);
	}

}
