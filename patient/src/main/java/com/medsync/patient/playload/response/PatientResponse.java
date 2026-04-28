package com.medsync.patient.playload.response;

import com.medsync.patient.model.Address;

public class PatientResponse {

	private Long patientId;

	private String firstName;

	private String lastName;

	private String email;

	private Long phnNumber;

	private Address address;

	public Long getPatientId() {
		return patientId;
	}

	public void setPatientId(Long id) {
		this.patientId = id;
	}

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

	public PatientResponse() {
		super();
	}

	public PatientResponse(Long patientId, String firstName, String lastName, String email, Long phnNumber,
			Address address) {
		super();
		this.patientId = patientId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phnNumber = phnNumber;
		this.address = address;
	}

}
