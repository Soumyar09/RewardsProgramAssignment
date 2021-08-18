package com.assignment.rewards.program.domain;

import java.time.Month;
import java.util.Map;

public class Customer {

	private Integer id;
	private String name;
	private Integer totalRewardPoints;
	private Map<Month, Integer> rewardPointsPerMonth;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getTotalRewardPoints() {
		return totalRewardPoints;
	}

	public void setTotalRewardPoints(Integer totalRewardPoints) {
		this.totalRewardPoints = totalRewardPoints;
	}

	public Map<Month, Integer> getRewardPointsPerMonth() {
		return rewardPointsPerMonth;
	}

	public void setRewardPointsPerMonth(Map<Month, Integer> rewardPointsPerMonth) {
		this.rewardPointsPerMonth = rewardPointsPerMonth;
	}

}
