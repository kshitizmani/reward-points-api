package com.charter.assignment.dto;



import java.util.Map;

/**
 * DTO (Data Transfer Object) representing the reward points details for a customer.
 * Contains the reward points earned by a customer for each month and the total points.
 */
public class RewardResponseDto {

    private String customerId;
    private Map<String, Integer> pointsPerMonth;
    private int totalPoints;

    /**
     * Constructs a new {@code RewardResponseDto} with the specified details.
     *
     * @param customerId the ID of the customer.
     * @param pointsPerMonth a map of month names to reward points earned in each month.
     * @param totalPoints the total reward points earned by the customer.
     */
    public RewardResponseDto(String customerId, Map<String, Integer> pointsPerMonth, int totalPoints) {
        this.customerId = customerId;
        this.pointsPerMonth = pointsPerMonth;
        this.totalPoints = totalPoints;
    }

    /**
     * Default constructor.
     */
    public RewardResponseDto() {
        super();
    }

    /**
     * getter and setter methods
     *
     */
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