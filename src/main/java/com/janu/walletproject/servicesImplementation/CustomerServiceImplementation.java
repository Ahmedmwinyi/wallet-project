package com.janu.walletproject.servicesImplementation;

import com.janu.walletproject.exceptions.AdminException;
import com.janu.walletproject.exceptions.CustomerException;
import com.janu.walletproject.exceptions.LoginException;
import com.janu.walletproject.model.Admin;
import com.janu.walletproject.model.Customer;
import com.janu.walletproject.model.User;
import com.janu.walletproject.model.Wallet;
import com.janu.walletproject.repository.CustomerRepo;
import com.janu.walletproject.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImplementation implements CustomerService {

	@Autowired
	private CustomerRepo customerRepo;

	@Autowired
	private LoginLogoutCustomerServiceImplementation loginLogoutCustomerServiceimplementation;

	@Autowired
	private LoginLogoutAdminServiceImplementation loginLogoutAdminServiceimplementation;

	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Override
	public Customer addCustomer(Customer customer) throws CustomerException {
		Optional<Customer> find_customer = customerRepo.findById(customer.getMobileNumber());

		if (find_customer.isEmpty()) {
			// Encrypt the password
			String encryptedPassword = passwordEncoder.encode(customer.getPassword());

			// Set the encrypted password for the User entity
			User user = new User();
			user.setMobileNumber(customer.getMobileNumber());
			user.setPassword(encryptedPassword);
			user.setRole(customer.getRole());

			customer.setUser(user);
			// Set the encrypted password for the Customer entity
			customer.setPassword(encryptedPassword);

			System.out.println(user);
			Customer added_customer = customerRepo.save(customer);

			Wallet wallet = new Wallet();
			System.out.println("200");

			wallet.setBalance(0.0);
			wallet.setWalletId(customer.getMobileNumber());
			customer.setWallet(wallet);

			System.out.println(customer);
			System.out.println(wallet);

			added_customer = customerRepo.save(customer);

			System.out.println("57");

			if (added_customer != null) {
				return added_customer;
			} else {
				throw new CustomerException("Oops, Sign Up Unsuccessful!");
			}
		} else {
			throw new CustomerException("Customer Already Registered With This Mobile Number: " + customer.getMobileNumber());
		}
	}


	@Override
	public Customer updateCustomer(String key, Customer customer) throws CustomerException, LoginException {

		Customer validate_customer = loginLogoutCustomerServiceimplementation.validateCustomer(key);

		if (validate_customer != null) {

			return customerRepo.save(customer);

		} else {
			throw new CustomerException("Invalid Key, Please Login In !");
		}

	}

	@Override
	public String removeCustomer(String key, String customerMobileNumber) throws CustomerException, LoginException {

		Customer validate_customer = loginLogoutCustomerServiceimplementation.validateCustomer(key);

		if (validate_customer != null) {

			customerRepo.deleteById(customerMobileNumber);

			return "Customer Deleted Successfully !";

		} else {
			throw new CustomerException("Invalid Key, Please Login In !");
		}

	}

	@Override
	public Customer viewCustomer(String key, String customerMobileNumber) throws CustomerException, LoginException {

		Customer validate_customer = loginLogoutCustomerServiceimplementation.validateCustomer(key);

		if (validate_customer != null) {

			Optional<Customer> optional_customer = customerRepo.findById(customerMobileNumber);

			if (optional_customer.isPresent()) {

				return optional_customer.get();
			} else {
				throw new CustomerException(
						"No Customer Found With The Customer Contact Number : " + customerMobileNumber);
			}

		} else {
			throw new CustomerException("Invalid Key, Please Login In !");
		}

	}

	@Override
	public List<Customer> viewAllCustomers(String key) throws AdminException, CustomerException, LoginException {

		Admin validate_admin = loginLogoutAdminServiceimplementation.validateAdmin(key);

		if (validate_admin != null) {

			List<Customer> listofcustomers = customerRepo.findAll();

			if (listofcustomers.isEmpty()) {
				throw new CustomerException("No Customers Available in the Database!");
			} else {
				return listofcustomers;
			}

		} else {
			throw new AdminException("Invalid Key, Please Login In as Admin!");
		}

	}

	@Override
	public Customer viewCustomerByMobileNumber(String mobileNumber) throws CustomerException {
		Optional<Customer> customerOpt = customerRepo.findById(mobileNumber);
		if (customerOpt.isPresent()) {
			return customerOpt.get();
		} else {
			throw new CustomerException("Customer not found");
		}
	}

}
