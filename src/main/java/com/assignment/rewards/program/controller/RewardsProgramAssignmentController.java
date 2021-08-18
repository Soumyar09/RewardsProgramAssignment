package com.assignment.rewards.program.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.assignment.rewards.program.domain.Customer;
import com.assignment.rewards.program.domain.CustomerTransaction;
import com.assignment.rewards.program.service.RewardsCalculatorService;

@RestController
public class RewardsProgramAssignmentController {

	@Autowired
	private RewardsCalculatorService rewardsCalculatorService;

	@RequestMapping(value = "/customerRewardPoints", method = RequestMethod.POST)
	public List<Customer> createProduct(@RequestBody List<CustomerTransaction> customerTransactions) {
		return rewardsCalculatorService.calculateRewardPointsForCustomers(customerTransactions);
	}
}
