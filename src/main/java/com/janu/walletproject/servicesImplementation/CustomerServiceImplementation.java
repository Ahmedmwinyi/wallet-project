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
import com.janu.walletproject.services.EmailService;
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
	private EmailService emailService;

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

			Wallet wallet = new Wallet();

			System.out.println("49");

			wallet.setBalance(0.0);

			wallet.setWalletId(customer.getMobileNumber());

			customer.setWallet(wallet);

			System.out.println(customer);
			System.out.println(wallet);

			Customer added_customer = customerRepo.save(customer);

			System.out.println("57");

			if (added_customer != null) {
				String emailSubject = "Welcome to Our Service!";
				String emailText = "Dear " + customer.getFirstName() + " " + customer.getLastName() +
						"Welcome to Our Wallet App! Your account has been successfully created, and you can now enjoy the convenience of managing your finances on the App.\n\n" +
						"Here are some of the features you can start using right away:\n" +
						"1. **Manage Your Balance:** Easily check your wallet balance and transaction history.\n" +
						"2. **Secure Transactions:** Enjoy secure and fast transactions with our encrypted payment system.\n" +
						"3. **Add Funds:** Add money to your wallet using various payment methods.\n" +
						"4. **Send and Receive Money:** Transfer money to friends and family instantly.\n" +
						"5. **Exclusive Offers:** Access special deals and promotions available only to our wallet users.\n\n" +
						"To get started, simply log in to your account using your registered mobile number and password.\n\n" +
						"If you have any questions or need assistance, our support team is here to help. Contact us at madiylal@gmail.com.\n\n" +
						"Best Regards,\n" +
						"The Wallet App Team";
				emailService.sendSimpleEmail(customer.getEmail(), emailSubject, emailText);

				return added_customer;

			} else {
				throw new CustomerException("OOps, Sign Up Unsuccessfull !");
			}
		} else {
			throw new CustomerException(
					"Customer Already Registered With This Mobile Number : " + customer.getMobileNumber());
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
