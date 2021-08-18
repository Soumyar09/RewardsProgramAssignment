package com.assignment.rewards.program.service;

import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;

import com.assignment.rewards.program.domain.Customer;
import com.assignment.rewards.program.domain.CustomerTransaction;

public class RewardsCalculatorServiceImpl implements RewardsCalculatorService {

	public List<Customer> calculateRewardPointsForCustomers(List<CustomerTransaction> customerTransactions) {
		List<Customer> customers = new ArrayList<Customer>();
		Set<Integer> customerIds = new HashSet<>();
		CollectionUtils.emptyIfNull(customerTransactions).stream().forEach(customerTransaction -> {
			customerIds.add(customerTransaction.getCustomerId());
		});

		customerIds.stream().forEach(customerId -> {
			Map<Month, Integer> rewardPointsMonthly = new HashMap<>();
			List<CustomerTransaction> distinctCustomerTransactions = customerTransactions.stream()
					.filter(customer -> customer.getCustomerId().intValue() == customerId.intValue())
					.collect(Collectors.toList());
			distinctCustomerTransactions.stream().forEach(customerTransaction -> {
				Date transactionDate = customerTransaction.getTransactionDate();
				Calendar cal = Calendar.getInstance();
				cal.setTime(transactionDate);

				Month month = Month.of(cal.get(Calendar.MONTH)+1);

				Float transactionAmount = customerTransaction.getTransactionAmount();
				Integer rewardPoints = calculateRewardPoints(transactionAmount);
				Integer savedPoints = rewardPointsMonthly.get(month);
				if (rewardPointsMonthly.get(month) == null) {
					rewardPointsMonthly.put(month, rewardPoints);
				} else {
					rewardPointsMonthly.put(month, rewardPoints + savedPoints);
				}
			});
			customers.add(populateCustomerDetails(customerId, rewardPointsMonthly,
					distinctCustomerTransactions.get(0).getCustomerName()));
		});

		return customers;
	}

	private Customer populateCustomerDetails(Integer customerId, Map<Month, Integer> rewardPointsMonthly,
			String customerName) {
		Customer customer = new Customer();
		customer.setId(customerId);
		customer.setName(customerName);
		customer.setRewardPointsPerMonth(rewardPointsMonthly);
		rewardPointsMonthly.forEach((month, points) -> {
			if(customer.getTotalRewardPoints() == null) {
				customer.setTotalRewardPoints(points);
			} else {
				customer.setTotalRewardPoints(customer.getTotalRewardPoints() + points);
			}
			
		});
		return customer;
	}

	private Integer calculateRewardPoints(Float transactionAmount) {
		Integer rewardPoints = 0;
		if (transactionAmount > 100) {
			rewardPoints = (Math.round(transactionAmount - 100) * 2) + 50;
		} else if (transactionAmount > 50) {
			rewardPoints = (Math.round(transactionAmount - 50) * 2);
		}
		return rewardPoints;
	}

}
