
package com.janu.walletproject.servicesImplementation;

import java.time.LocalDateTime;
import java.util.Optional;

import com.janu.walletproject.exceptions.CustomerException;
import com.janu.walletproject.exceptions.LoginException;
import com.janu.walletproject.exceptions.LogoutException;
import com.janu.walletproject.exceptions.UserException;
import com.janu.walletproject.model.CurrentCustomerSession;
import com.janu.walletproject.model.Customer;
import com.janu.walletproject.model.User;
import com.janu.walletproject.repository.CurrentCustomerSessionRepo;
import com.janu.walletproject.repository.CustomerRepo;
import com.janu.walletproject.services.LoginLogoutCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import net.bytebuddy.utility.RandomString;

@Service
public class LoginLogoutCustomerServiceImplementation implements LoginLogoutCustomerService {

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private CurrentCustomerSessionRepo currentCustomerSessionRepo;

	@Override
	public CurrentCustomerSession loginCustomer(User user) throws LoginException, CustomerException {
		BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
		if ("Customer".equals(user.getRole())) {
			Optional<Customer> optional_customer = customerRepo.findById(user.getMobileNumber());

			if (optional_customer.isPresent()) {
				Customer customer = optional_customer.get();
				Optional<CurrentCustomerSession> optional_CurrentUserSession = Optional.ofNullable(currentCustomerSessionRepo
						.findByCustomerMobileNumber(customer.getMobileNumber()));

				if (optional_CurrentUserSession.isPresent()) {
					throw new LoginException(
							"User Already Logged In With This Customer Id: " + customer.getMobileNumber());
				} else {
					// Compare the password using bcrypt
					if (bcrypt.matches(user.getPassword(), customer.getPassword())) {
						CurrentCustomerSession currentCustomerSession = new CurrentCustomerSession();
						String key = RandomString.make(6);

						currentCustomerSession.setCustomerMobileNumber(customer.getMobileNumber());
						currentCustomerSession.setKey(key);
						currentCustomerSession.setLocalDateTime(LocalDateTime.now());

						return currentCustomerSessionRepo.save(currentCustomerSession);
					} else {
						throw new LoginException("Invalid User_Id or Password");
					}
				}
			} else {
				throw new CustomerException("No Registered Customer Found With This User_Id: " + user.getMobileNumber());
			}
		} else {
			throw new LoginException("Please, Select Customer as Role to Login!");
		}
	}



	@Override
	public String logoutCustomer(String key) throws LogoutException {

		Optional<CurrentCustomerSession> currentCustomerSession = currentCustomerSessionRepo.findByKey(key);

		if (currentCustomerSession.isPresent()) {

			currentCustomerSessionRepo.delete(currentCustomerSession.get());

			return "Logged Out Successfully !";

		} else {
			throw new LogoutException("Invalid Key, No User Logged In !");
		}

	}

	// Use methods for Payments or some Very Highly Secured Operations//
	@Override
	public User authenticateCustomer(User user, String key) throws UserException, LoginException, CustomerException {

		Optional<CurrentCustomerSession> optional_currentCustomerSession = currentCustomerSessionRepo.findByKey(key);

		if (optional_currentCustomerSession.isPresent()) {

			CurrentCustomerSession currentCustomerSession = optional_currentCustomerSession.get();

			Optional<Customer> optional_customer = customerRepo
					.findById(currentCustomerSession.getCustomerMobileNumber());

			if (optional_customer.isPresent()) {

				Customer customer = optional_customer.get();

				if (customer.getMobileNumber().equals(user.getMobileNumber())
						&& customer.getPassword().equals(user.getPassword())) {

					return user;
				} else {
					throw new UserException("Invalid UserId or Password");
				}

			} else {
				throw new CustomerException("No Customer Found with this Customer Id : "
						+ currentCustomerSession.getCustomerMobileNumber());
			}

		} else {
			throw new LoginException("Invalid Key, Please Login In !");
		}
	}

	@Override
	public Customer validateCustomer(String key) throws CustomerException, LoginException {

		Optional<CurrentCustomerSession> optional_currentCustomerSession = currentCustomerSessionRepo.findByKey(key);

		if (optional_currentCustomerSession.isPresent()) {

			CurrentCustomerSession currentCustomerSession = optional_currentCustomerSession.get();

			Optional<Customer> optional_customer = customerRepo
					.findById(currentCustomerSession.getCustomerMobileNumber());

			if (optional_customer.isPresent()) {

				Customer customer = optional_customer.get();

				return customer;

			} else {
				throw new CustomerException("No Customer Found with this Customer Id : "
						+ currentCustomerSession.getCustomerMobileNumber());
			}

		} else {
			throw new LoginException("Invalid Key, Please Login In !");
		}
	}

}
