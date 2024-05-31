package com.janu.walletproject.servicesImplementation;

import java.util.List;
import java.util.Optional;

import com.janu.walletproject.exceptions.AddressException;
import com.janu.walletproject.exceptions.AdminException;
import com.janu.walletproject.exceptions.CustomerException;
import com.janu.walletproject.exceptions.LoginException;
import com.janu.walletproject.model.Address;
import com.janu.walletproject.model.Admin;
import com.janu.walletproject.model.Customer;
import com.janu.walletproject.repository.AddressRepo;
import com.janu.walletproject.services.AddressService;
import com.janu.walletproject.services.LoginLogoutAdminService;
import com.janu.walletproject.services.LoginLogoutCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImplementation implements AddressService {

	@Autowired
	private AddressRepo addressRepo;

	@Autowired
	private LoginLogoutCustomerService loginLogoutCustomerServiceImplementation;

	@Autowired
	private LoginLogoutAdminService loginLogoutAdminServiceImplementation;


	@Override
	public String addAddress(String key, Address address) throws CustomerException, LoginException {

		Customer customer = loginLogoutCustomerServiceImplementation.validateCustomer(key);

		if (customer != null) {

			customer.setAddress(address);

			addressRepo.save(address);

			return "Address Added Successfully !";

		} else {
			throw new CustomerException("No Customer Found, Please Login In !");
		}
	}

	@Override
	public String updateAddress(String key, Address address) throws CustomerException, LoginException {

		Customer customer = loginLogoutCustomerServiceImplementation.validateCustomer(key);

		if (customer != null) {

			Address old_address = customer.getAddress();
			

			customer.setAddress(address);



			addressRepo.deleteById(old_address.getAddressId());

			return "Address Updated Successfully !";

		} else {
			throw new CustomerException("No Customer Found, Please Login In !");
		}

	}

	@Override
	public String removeAddress(String key, Integer addressId)
			throws CustomerException, LoginException, AddressException {

		Customer customer = loginLogoutCustomerServiceImplementation.validateCustomer(key);

		if (customer != null) {

			Optional<Address> optional_address = addressRepo.findById(addressId);

			if (optional_address.isPresent()) {

				addressRepo.delete(optional_address.get());

				return "Address Deleted Successfully !";

			} else {
				throw new AddressException("No Address Found With The Address ID : " + addressId);
			}

		} else {
			throw new CustomerException("No Customer Found, Please Login In !");
		}
	}

	@Override
	public List<Address> viewAllAddress(String key) throws AdminException, LoginException, AddressException {

		Admin admin = loginLogoutAdminServiceImplementation.validateAdmin(key);

		if (admin != null) {

			List<Address> listofaddresses = addressRepo.findAll();

			if (!listofaddresses.isEmpty()) {

				return listofaddresses;
			} else {
				throw new AddressException("No Addresses Found in The Database !");
			}

		} else {
			throw new AdminException("Invalid Key, Please Login In as Admin !");
		}

	}

	@Override
	public Address viewAddress(String key) throws CustomerException, LoginException, AddressException {

		Customer customer = loginLogoutCustomerServiceImplementation.validateCustomer(key);

		if (customer != null) {

			Address address = customer.getAddress();

			if (address != null) {

				return address;

			} else {
				throw new AddressException("No Addresses Has Been Added By The Customer !");
			}

		} else {
			throw new CustomerException("No Customer Found, Please Login In !");
		}
	}

}
