package com.medsync.patient.playload.request;

import com.medsync.patient.model.Address;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class PatientRequest {
	
	@NotBlank(message = "First name is required")
	@Size(min = 2, message = "Name must be at least 2 characters")
	private String firstName;
	
	@NotBlank(message = "Last name is required")
	@Size(min = 2, message = "Name must be at least 2 characters")
	private String lastName;
	
	@Email(message = "invalid email format")
	@NotBlank(message = "Email is required")
	private String email;
	
	@NotNull(message = "Phone number is required")
	@Min(value = 1000000000L, message = "Phone number must be 10 digits")
	@Max(value = 9999999999L, message = "Phone number must be 10 digits")
	private Long phnNumber;
	
	
	private Address address;


	public String getFirstName() {
		return firstName;
	}


	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}


	public String getLastName() {
		return lastName;
	}


	public void setLastName(String lastName) {
		this.lastName = lastName;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Long getPhnNumber() {
		return phnNumber;
	}


	public void setPhnNumber(Long phnNumber) {
		this.phnNumber = phnNumber;
	}


	public Address getAddress() {
		return address;
	}


	public void setAddress(Address address) {
		this.address = address;
	}

	
	
	public PatientRequest() {
		super();
	}


	public PatientRequest(@NotBlank(message = "First name is required") String firstName,
			@NotBlank(message = "Last name is required") String lastName,
			@Email(message = "invalid email format") String email,
			@NotNull(message = "phone number is required") @Digits(message = "Numbers only", integer = 10, fraction = 0) Long phnNumber,
			Address address) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phnNumber = phnNumber;
		this.address = address;
	}
	
	

}
