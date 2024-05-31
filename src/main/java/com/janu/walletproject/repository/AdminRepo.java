package com.janu.walletproject.repository;

import java.util.Optional;

import com.janu.walletproject.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Integer> {

	public Optional<Admin> findByMobileNumber(String mobileNumber);
	
}
