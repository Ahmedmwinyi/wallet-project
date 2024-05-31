/**
 * 
 */
package com.janu.walletproject.services;

import com.janu.walletproject.exceptions.CurrentCustomerSessionException;
import com.janu.walletproject.exceptions.CustomerException;
import com.janu.walletproject.model.CurrentCustomerSession;
import com.janu.walletproject.model.Customer;

public interface CurrentCustomerSessionService {

	public CurrentCustomerSession getCurrentCustomerSession(String key) throws CurrentCustomerSessionException;

	public Customer getCustomerDetails(String key) throws CurrentCustomerSessionException, CustomerException;

	public String getCurrentCustomerId(String key) throws CurrentCustomerSessionException;

	public String getSessionKeyByMobileNumber(String customerMobileNumber) throws Exception;

}
