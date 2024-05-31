/**
 * 
 */
package com.janu.walletproject.services;

import com.janu.walletproject.exceptions.CustomerException;
import com.janu.walletproject.exceptions.LoginException;
import com.janu.walletproject.exceptions.LogoutException;
import com.janu.walletproject.exceptions.UserException;
import com.janu.walletproject.model.CurrentCustomerSession;
import com.janu.walletproject.model.Customer;
import com.janu.walletproject.model.User;


public interface LoginLogoutCustomerService {

	public CurrentCustomerSession loginCustomer(User user) throws LoginException, CustomerException;

	public String logoutCustomer(String key) throws LogoutException;
	
	public User authenticateCustomer(User user, String key) throws UserException, LoginException, CustomerException;

	public Customer validateCustomer(String key) throws LoginException, CustomerException;
}
