package com.janu.walletproject.repository;

import java.util.Optional;

import com.janu.walletproject.model.CurrentCustomerSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrentCustomerSessionRepo extends JpaRepository<CurrentCustomerSession, Integer> {

	public Optional<CurrentCustomerSession> findByKey(String key);

	public CurrentCustomerSession findByCustomerMobileNumber(String customerMobileNumber);
}
