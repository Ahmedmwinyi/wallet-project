
package com.janu.walletproject.repository;

import com.janu.walletproject.model.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface BankAccountRepo extends JpaRepository<BankAccount, String> {

}
