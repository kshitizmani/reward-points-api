package com.charter.assignment.dto;



import java.util.Map;


public class RewardResponseDto {
	
	private String customerId;
    private Map<String, Integer> pointsPerMonth;
    private int totalPoints;
    
	public RewardResponseDto(String customerId, Map<String, Integer> pointsPerMonth, int totalPoints) {
		super();
		this.customerId = customerId;
		this.pointsPerMonth = pointsPerMonth;
		this.totalPoints = totalPoints;
	}

	public RewardResponseDto() {
		super();
	
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Map<String, Integer> getPointsPerMonth() {
		return pointsPerMonth;
	}

	public void setPointsPerMonth(Map<String, Integer> pointsPerMonth) {
		this.pointsPerMonth = pointsPerMonth;
	}

	public int getTotalPoints() {
		return totalPoints;
	}

	public void setTotalPoints(int totalPoints) {
		this.totalPoints = totalPoints;
	}

    
    
    
    
    
    
}
