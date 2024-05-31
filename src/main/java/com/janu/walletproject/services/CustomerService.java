/**
 * 
 */
package com.janu.walletproject.services;

import java.util.List;

import com.janu.walletproject.exceptions.AdminException;
import com.janu.walletproject.exceptions.CustomerException;
import com.janu.walletproject.exceptions.LoginException;
import com.janu.walletproject.model.Customer;

public interface CustomerService {

	public Customer addCustomer(Customer customer) throws CustomerException;

	public Customer updateCustomer(String key, Customer customer) throws CustomerException, LoginException;

	public String removeCustomer(String key, String customer_Id) throws CustomerException, LoginException;

	public Customer viewCustomer(String key, String customer_Id) throws CustomerException, LoginException;

	// Check for Admin Role
	public List<Customer> viewAllCustomers(String key) throws AdminException, LoginException, CustomerException;

	public Customer viewCustomerByMobileNumber(String mobileNumber) throws CustomerException;



}
