package com.janu.walletproject.repository;

import com.janu.walletproject.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User, String> {
}
