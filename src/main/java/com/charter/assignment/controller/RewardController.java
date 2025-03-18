package com.charter.assignment.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.charter.assignment.dto.RewardResponseDto;
import com.charter.assignment.service.RewardService;

@RestController
@RequestMapping("/api/rewards")
public class RewardController {
	
	private final RewardService rewardService;

    public RewardController(RewardService rewardService) {
        this.rewardService = rewardService;
    }
    //GET method to retrieve the reward points earned by a customer for each month and the total points.
    
    @GetMapping("/{customerId}")
    public RewardResponseDto getRewardPoints(@PathVariable String customerId) {
        return rewardService.calculateRewardPoints(customerId);
    }

}
