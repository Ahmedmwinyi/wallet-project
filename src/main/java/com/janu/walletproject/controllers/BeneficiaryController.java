package com.janu.walletproject.controllers;

import com.janu.walletproject.exceptions.*;
import com.janu.walletproject.model.Beneficiary;
import com.janu.walletproject.model.User;
import com.janu.walletproject.services.BeneficiaryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/beneficiary")
public class BeneficiaryController {

	@Autowired
	private BeneficiaryService beneficiaryService;

	@PostMapping("/add")
	public ResponseEntity<Beneficiary> addBeneficiaryHandler(@RequestParam String key,
															 @Valid @RequestBody Beneficiary beneficiary)
			throws CustomerException, LoginException, BeneficiaryException {

		Beneficiary saved_beneficiary = beneficiaryService.addBeneficiary(key, beneficiary);

		return new ResponseEntity<>(saved_beneficiary, HttpStatus.ACCEPTED);

	}

	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteBeneficiaryHandler(@RequestParam String key,
			@Valid @RequestParam String beneficiaryMobileNumber)
			throws CustomerException, LoginException, BeneficiaryException {

		String result = beneficiaryService.deleteBeneficiary(key, beneficiaryMobileNumber);

		return new ResponseEntity<>(result, HttpStatus.ACCEPTED);

	}

	@PostMapping("/viewall")
	public ResponseEntity<List<Beneficiary>> viewAllBeneficiariesHandler(@RequestParam String key)
			throws BeneficiaryException, UserException, AdminException, LoginException, CustomerException {

		List<Beneficiary> listofbeneficiaries = beneficiaryService.viewAllBeneficiaries(key);

		return new ResponseEntity<>(listofbeneficiaries, HttpStatus.OK);
	}

	@PostMapping("/viewallbyCustomer")
	public ResponseEntity<List<Beneficiary>> viewAllBeneficiariesByCustomerHandler(@Valid @RequestBody User user,
			@RequestParam String key, @RequestParam String mobileNumber)
			throws BeneficiaryException, UserException, AdminException, LoginException, CustomerException {

		List<Beneficiary> listofbeneficiaries = beneficiaryService.viewAllBeneficiariesByCustomer(user, key,
				mobileNumber);

		return new ResponseEntity<>(listofbeneficiaries, HttpStatus.OK);
	}

}
