/**
 * 
 */
package com.janu.walletproject.services;

import java.util.List;

import com.janu.walletproject.exceptions.CustomerException;
import com.janu.walletproject.exceptions.LoginException;
import com.janu.walletproject.exceptions.WalletException;
import com.janu.walletproject.model.Bill;
import com.janu.walletproject.model.Transaction;

public interface BillService {

	public Transaction BillPayment(String key, Bill bill) throws CustomerException, LoginException, WalletException;

	public List<Bill> viewBillPayments(String key) throws CustomerException, LoginException;

}
