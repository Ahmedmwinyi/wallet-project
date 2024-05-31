
package com.janu.walletproject.services;

import com.janu.walletproject.exceptions.*;
import com.janu.walletproject.model.Transaction;
import com.janu.walletproject.model.User;
public interface WalletService {

	public Double showWalletBalance(String key) throws LoginException, CustomerException;

	public Transaction transferFunds(User user, String key, String targetMobileNumber, Double amount,
									 String description) throws LoginException, CustomerException, WalletException, UserException;

	public Transaction addMoneyToWallet(User user, String key, Double amount)
			throws UserException, LoginException, CustomerException, WalletException, BankAccountException;

	public Transaction addMoneyToBank(User user, String key, Double amount) throws UserException, LoginException, CustomerException, WalletException, BankAccountException;

	public Transaction transferFundsToBeneficiary(User user, String key, String benficiarymobileNumber, Double amount,
			String description) throws LoginException, CustomerException, WalletException, UserException, BankAccountException, BeneficiaryException;

}
