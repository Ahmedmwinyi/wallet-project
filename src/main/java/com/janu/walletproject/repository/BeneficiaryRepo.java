package com.janu.walletproject.repository;

import com.janu.walletproject.model.Beneficiary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BeneficiaryRepo extends JpaRepository<Beneficiary, Integer> {


}
