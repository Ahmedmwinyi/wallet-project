package com.janu.walletproject.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer addressId;

	@NotBlank(message = "{Address.streetname.invalid}")
	private String streetName;

	@NotBlank(message = "{Address.buildingname.invalid}")
	private String buildingName;

	@NotBlank(message = "{Address.city.invalid}")
	private String city;

	@NotBlank(message = "{Address.state.invalid}")
	private String state;

	@NotBlank(message = "{Address.landmark.invalid}")
	private String country;
}
