package com.medsync.patient.mapper;

import org.springframework.stereotype.Component;

import com.medsync.patient.model.Address;
import com.medsync.patient.model.Patient;
import com.medsync.patient.playload.request.PatientRequest;
import com.medsync.patient.playload.response.PatientResponse;

@Component
public class PatientMapper {

	public PatientResponse responseMapper(Patient newPatient) {

		PatientResponse response = new PatientResponse();

		response.setPatientId(newPatient.getPatientId());
		response.setAddress(newPatient.getAddress());
		response.setFirstName(newPatient.getFirstName());
		response.setLastName(newPatient.getLastName());
		response.setEmail(newPatient.getEmail());
		response.setPhnNumber(newPatient.getPhnNumber());

		return response;
	}

	public void updatePatientFromRequest(Patient existing, PatientRequest request) {
		if (request == null)
			return;

		if (request.getFirstName() != null)
			existing.setFirstName(request.getFirstName());
		if (request.getLastName() != null)
			existing.setLastName(request.getLastName());
		if (request.getEmail() != null)
			existing.setEmail(request.getEmail());
		if (request.getPhnNumber() != null)
			existing.setPhnNumber(request.getPhnNumber());

		if (request.getAddress() != null) {
			updateAddressFromRequest(existing, request.getAddress());
		}
	}

	private void updateAddressFromRequest(Patient patient, Address requestAddr) {
        Address currentAddr = patient.getAddress();
        if (currentAddr == null) {
            currentAddr = new Address();
            patient.setAddress(currentAddr);
        }
        
        if (requestAddr.getCity() != null) currentAddr.setCity(requestAddr.getCity());
        if (requestAddr.getStreet() != null) currentAddr.setStreet(requestAddr.getStreet());
        if (requestAddr.getState() != null) currentAddr.setState(requestAddr.getState());
        if (requestAddr.getZipcode() != null) currentAddr.setZipcode(requestAddr.getZipcode());
        if (requestAddr.getCountry() != null) currentAddr.setCountry(requestAddr.getCountry());
    }

}
