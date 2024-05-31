/**
 * 
 */
package com.janu.walletproject.services;

import java.util.List;

import com.janu.walletproject.exceptions.*;
import com.janu.walletproject.model.BankAccount;
import com.janu.walletproject.model.User;

public interface BankAccountService {

	String addAccount(User user, String key, String mobileNumber, String bankName, String ifscCode, Double balance,
					  String accountNo) throws CurrentCustomerSessionException, UserException, LoginException, CustomerException;

	public String removeAccount(User user, String key) throws UserException, LoginException, CustomerException,
			BankAccountException, CurrentCustomerSessionException;

	public BankAccount viewAccount(User user, String key) throws UserException, LoginException, CustomerException,
			BankAccountException, CurrentCustomerSessionException;

	public Double viewBankBalance(User user, String key) throws UserException, LoginException, CustomerException,
			BankAccountException, CurrentCustomerSessionException;

	// Admin
	public List<BankAccount> viewAllAccounts(User user, String key)
			throws UserException, AdminException, LoginException, BankAccountException, CurrentAdminSessionException;

}
