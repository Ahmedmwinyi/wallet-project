/**
 * 
 */
package com.janu.walletproject.controllers;

import com.janu.walletproject.exceptions.AdminException;
import com.janu.walletproject.exceptions.CustomerException;
import com.janu.walletproject.exceptions.LoginException;
import com.janu.walletproject.model.Customer;
import com.janu.walletproject.services.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
@CrossOrigin(origins = "*")
public class CustomerController {

	@Autowired
	private CustomerService customerService;

	@PostMapping("/add")
	public ResponseEntity<Customer> addCustomerHandler(@Valid @RequestBody Customer customer) throws CustomerException {

		Customer added_customer = customerService.addCustomer(customer);

		return new ResponseEntity<Customer>(added_customer, HttpStatus.ACCEPTED);

	}

	@PutMapping("/update")
	public ResponseEntity<Customer> updateCustomerHandler(@RequestParam String key,
			@Valid @RequestBody Customer customer) throws CustomerException, LoginException {

		Customer updated_customer = customerService.updateCustomer(key, customer);

		return new ResponseEntity<Customer>(updated_customer, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<String> removeCustomerHandler(@RequestParam String key,
			@Valid @RequestParam String mobileNumber) throws CustomerException, LoginException {

		String result = customerService.removeCustomer(key, mobileNumber);

		return new ResponseEntity<String>(result, HttpStatus.OK);

	}

	@GetMapping("/view")
	public ResponseEntity<Customer> viewCustomerHandler(@RequestParam String key,
			@Valid @RequestParam String mobileNumber) throws CustomerException, LoginException {

		Customer customer = customerService.viewCustomer(key, mobileNumber);

		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	// Check for Admin Role
	@GetMapping("/viewall")
	public ResponseEntity<List<Customer>> viewAllCustomersHandler(@RequestParam String key)
			throws CustomerException, LoginException, AdminException {

		List<Customer> listofcustomers = customerService.viewAllCustomers(key);

		return new ResponseEntity<List<Customer>>(listofcustomers, HttpStatus.OK);
	}

	@GetMapping("/firstname")
	public ResponseEntity<String> getFirstName(@RequestParam String mobileNumber) throws CustomerException {
		Customer customer = customerService.viewCustomerByMobileNumber(mobileNumber);
		String firstName = customer.getFirstName();
		return new ResponseEntity<>(firstName, HttpStatus.OK);
	}

}
