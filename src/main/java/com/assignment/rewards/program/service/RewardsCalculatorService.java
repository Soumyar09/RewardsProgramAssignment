package com.assignment.rewards.program.service;

import java.util.List;

import com.assignment.rewards.program.domain.Customer;
import com.assignment.rewards.program.domain.CustomerTransaction;

public interface RewardsCalculatorService {

	public List<Customer> calculateRewardPointsForCustomers(List<CustomerTransaction> customerTransactions);
}
