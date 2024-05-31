package com.janu.walletproject.servicesImplementation;

import java.util.List;

import com.janu.walletproject.exceptions.CustomerException;
import com.janu.walletproject.exceptions.LoginException;
import com.janu.walletproject.exceptions.WalletException;
import com.janu.walletproject.model.Bill;
import com.janu.walletproject.model.Customer;
import com.janu.walletproject.model.Transaction;
import com.janu.walletproject.model.Wallet;
import com.janu.walletproject.repository.WalletRepo;
import com.janu.walletproject.services.BillService;
import com.janu.walletproject.services.TransactionService;
import org.hibernate.TransactionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillServiceImplementation implements BillService {

	@Autowired
	private LoginLogoutCustomerServiceImplementation loginLogoutCustomerServiceImplementation;

	@Autowired
	private TransactionService transactionService;

	@Autowired
	private WalletRepo walletRepo;

	@Override
	public Transaction BillPayment(String key, Bill bill) throws CustomerException, LoginException, WalletException {

		Customer customer = loginLogoutCustomerServiceImplementation.validateCustomer(key);

		if (customer != null) {

			Wallet wallet = customer.getWallet();

			Double availablebalance = wallet.getBalance();

			List<Bill> listofbills = wallet.getListofBills();

			// make a Transaction and add transaction to transaction list

			if (availablebalance >= bill.getAmount()) {

				Transaction transaction = transactionService.addTransaction(key, bill.getReceiver(), bill.getBillType(),
						"Bill Payment", bill.getAmount());

				if (transaction != null) {

					listofbills.add(bill);

					wallet.setBalance(availablebalance - bill.getAmount());

					wallet.setListofBills(listofbills);

					walletRepo.save(wallet);

					return transaction;
				}

				else {
					throw new TransactionException("Opps ! Transaction Failed !");

				}
			} else {
				throw new WalletException("Insufficient Funds ! Available Wallet Balance : " + availablebalance);
			}

		} else {
			throw new CustomerException("Invalid Customer Key, Please Login In ! ");
		}

	}

	@Override
	public List<Bill> viewBillPayments(String key) throws CustomerException, LoginException {

		Customer customer = loginLogoutCustomerServiceImplementation.validateCustomer(key);

		if (customer != null) {

			Wallet wallet = customer.getWallet();

			return wallet.getListofBills();

		} else {
			throw new CustomerException("Invalid Customer Key, Please Login In ! ");
		}

	}

}
