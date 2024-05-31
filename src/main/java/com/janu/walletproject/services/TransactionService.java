/**
 * 
 */
package com.janu.walletproject.services;

import java.util.List;

import com.janu.walletproject.exceptions.AdminException;
import com.janu.walletproject.exceptions.CustomerException;
import com.janu.walletproject.exceptions.LoginException;
import com.janu.walletproject.model.Transaction;

public interface TransactionService {

	public Transaction addTransaction(String key, String receiver, String description, String transactionType,
									  Double amount) throws CustomerException, LoginException;

	public Transaction viewTransaction(String key, Integer transactionId) throws CustomerException, LoginException;

	public List<Transaction> viewAllTransactions(String key) throws CustomerException, LoginException;

	// Admin
	public List<Transaction> viewAllTransactionsByCustomer(String key, String mobileNumber)
			throws AdminException, LoginException, CustomerException;

	// Admin
	public List<Transaction> viewAllTransactionsByCustomerByDate(String key, String date, String mobileNumber)
			throws AdminException, LoginException, CustomerException;

	public List<Transaction> viewTransactionByDate(String key, String date) throws LoginException, CustomerException;

}
