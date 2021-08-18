package com.assignment.rewards.program.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.assignment.rewards.program.service.RewardsCalculatorService;
import com.assignment.rewards.program.service.RewardsCalculatorServiceImpl;

@Configuration
public class RewardsProgramAssignmentConfig {

	@Bean
	public RewardsCalculatorService rewardsCalculatorService() {
		return new RewardsCalculatorServiceImpl();
	}
}
