package com.janu.walletproject.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class BankAccount {

	@Id
	@NotBlank(message = "{Wallet.id.invalid}")
	@Size(min = 10, max = 12, message = "{Wallet.id.invalid}")
	private String walletId;

	@NotBlank(message = "{Bank.Account.invalid}")
	@Size(min = 10, max = 13, message = "{Bank.Account.invalid}")
	private String accountNo;

	@NotBlank(message = "{Bank.card.invalid}")
	private String cardNumber;

	@NotBlank(message = "{Bank.Name.invalid}")
	private String bankName;

	@NotNull(message = "{Bank.Balance.invalid}")
	@Min(value = 0, message = "Minimum Balance Should be Zero !")
	private Double balance;
}
