/**
 * 
 */
package com.janu.walletproject.services;

import java.util.List;

import com.janu.walletproject.exceptions.*;
import com.janu.walletproject.model.Beneficiary;
import com.janu.walletproject.model.User;

public interface BeneficiaryService {

	public Beneficiary addBeneficiary(String key, Beneficiary beneficiary)
			throws CustomerException, LoginException, BeneficiaryException;

	public String deleteBeneficiary(String key, String beneficiaryMobileNumber)
			throws CustomerException, LoginException, BeneficiaryException;

	public List<Beneficiary> viewAllBeneficiaries(String key)
			throws BeneficiaryException, UserException, LoginException, CustomerException;

	// Admin
	public List<Beneficiary> viewAllBeneficiariesByCustomer(User user, String key, String customerMobileNumber)
			throws BeneficiaryException, UserException, LoginException, CustomerException, AdminException;

}
