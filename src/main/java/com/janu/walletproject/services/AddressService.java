/**
 * 
 */
package com.janu.walletproject.services;

import java.util.List;

import com.janu.walletproject.exceptions.AddressException;
import com.janu.walletproject.exceptions.AdminException;
import com.janu.walletproject.exceptions.CustomerException;
import com.janu.walletproject.exceptions.LoginException;
import com.janu.walletproject.model.Address;

public interface AddressService {

	public String addAddress(String key, Address address) throws CustomerException, LoginException;

	public String updateAddress(String key, Address address) throws CustomerException, LoginException;

	public String removeAddress(String key, Integer addressId)
			throws CustomerException, LoginException, AddressException;

	// Admin
	public List<Address> viewAllAddress(String key) throws AdminException, LoginException, AddressException;

	public Address viewAddress(String key) throws CustomerException, LoginException, AddressException;

}
