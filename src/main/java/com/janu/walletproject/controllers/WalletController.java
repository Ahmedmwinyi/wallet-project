/**
 * 
 */
package com.janu.walletproject.controllers;


import com.janu.walletproject.exceptions.*;
import com.janu.walletproject.model.Transaction;
import com.janu.walletproject.model.User;
import com.janu.walletproject.services.WalletService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wallet")
@CrossOrigin(origins = "*")
public class WalletController {

	@Autowired
	private WalletService walletService;

	@GetMapping("/balance")
	public ResponseEntity<Double> showWalletBalanceHandler(@RequestParam String key)
			throws LoginException, CustomerException {

		Double balance = walletService.showWalletBalance(key);

		return new ResponseEntity<Double>(balance, HttpStatus.OK);

	}

	@PostMapping("/transfer")
	public ResponseEntity<Transaction> transferFundsHandler(@Valid @RequestBody User user, @RequestParam String key,
															@Valid @RequestParam String targetMobileNumber, @Valid @RequestParam Double amount,
															@Valid @RequestParam String description)
			throws LoginException, CustomerException, WalletException, UserException {

		Transaction transaction = walletService.transferFunds(user, key, targetMobileNumber, amount, description);

		return new ResponseEntity<Transaction>(transaction, HttpStatus.ACCEPTED);

	}

	@PostMapping("/transfertobeneficiary")
	public ResponseEntity<Transaction> transferFundsToBeneficiaryHandler(@Valid @RequestBody User user,
			@RequestParam String key, @Valid @RequestParam String beneficiaryMobileNumber,
			@Valid @RequestParam Double amount, @Valid @RequestParam String description) throws LoginException,
			CustomerException, WalletException, UserException, BankAccountException, BeneficiaryException {

		Transaction transaction = walletService.transferFundsToBeneficiary(user, key, beneficiaryMobileNumber, amount,
				description);

		return new ResponseEntity<Transaction>(transaction, HttpStatus.ACCEPTED);

	}

	@PostMapping("/transfer/towallet")
	public ResponseEntity<Transaction> addMoneyToWalletHandler(@Valid @RequestBody User user, @RequestParam String key,
			@Valid @RequestParam Double amount)
			throws UserException, LoginException, CustomerException, WalletException, BankAccountException {

		Transaction transaction = walletService.addMoneyToWallet(user, key, amount);

		return new ResponseEntity<Transaction>(transaction, HttpStatus.ACCEPTED);
	}

	@PostMapping("/transfer/tobank")
	public ResponseEntity<Transaction> addMoneyToBank(@Valid @RequestBody User user, @RequestParam String key,
			@Valid @RequestParam Double amount)
			throws UserException, LoginException, CustomerException, WalletException, BankAccountException {

		Transaction transaction = walletService.addMoneyToBank(user, key, amount);

		return new ResponseEntity<Transaction>(transaction, HttpStatus.ACCEPTED);
	}

}
