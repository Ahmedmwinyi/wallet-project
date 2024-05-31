package com.janu.walletproject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Beneficiary {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer beneficiaryId;

	@NotBlank(message = "{Beneficiary.Contact.invalid}")
	@Size(min = 10, max = 13, message = "{Beneficiary.Contact.invalid}")
	private String mobileNumber;

	@NotBlank(message = "{Beneficiary.Name.invalid}")
	private String name;

	@NotBlank(message = "{Beneficiary.Account.invalid}")
	private String accountNo;

	private String cardNumber;

	@NotBlank(message = "{Beneficiary.BankName.invalid}")
	private String bankName;

}
