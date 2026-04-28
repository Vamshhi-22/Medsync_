package com.medsync.patient.service.impl;

import java.util.List;  


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.medsync.patient.Exception.PatientNotFoundException;
import com.medsync.patient.advice.GlobelExceptionHandler;
import com.medsync.patient.mapper.PatientMapper;
import com.medsync.patient.model.Address;
import com.medsync.patient.model.Patient;
import com.medsync.patient.playload.request.PatientRequest;
import com.medsync.patient.playload.response.PatientResponse;
import com.medsync.patient.repository.PatientRepository;
import com.medsync.patient.service.PatientService;

@Service
public class PatientServiceImpl implements PatientService {

	private final GlobelExceptionHandler globelExceptionHandler;

	private final PatientRepository patientRepo;

	private final PatientMapper mapper;

	public PatientServiceImpl(PatientRepository patientRepo, PatientMapper mapper,
			GlobelExceptionHandler globelExceptionHandler) {

		this.patientRepo = patientRepo;

		this.mapper = mapper;

		this.globelExceptionHandler = globelExceptionHandler;
	}

	@Override
	public PatientResponse createPatient(PatientRequest request) {

		Patient newPatient = new Patient();
		newPatient.setEmail(request.getEmail());
		newPatient.setPhnNumber(request.getPhnNumber());
		newPatient.setFirstName(request.getFirstName());
		newPatient.setLastName(request.getLastName());

		Address address = new Address();
		if (request.getAddress() != null) {
			address.setCity(request.getAddress().getCity());
			address.setCountry(request.getAddress().getCountry());
			address.setZipcode(request.getAddress().getZipcode());
			address.setState(request.getAddress().getState());
			address.setStreet(request.getAddress().getStreet());

			newPatient.setAddress(address);

		}
		patientRepo.save(newPatient);
		return mapper.responseMapper(newPatient);
	}

	@Override
	public PatientResponse findById(Long id) {

		Patient response = patientRepo.findById(id).orElseThrow(() -> new PatientNotFoundException("Not found"));

		if (!response.isActive()) { 
			throw new PatientNotFoundException("Patient is deleted");
		}
		return mapper.responseMapper(response);

	}

	@Override
	public PatientResponse getPatientByEmail(String email) {

		Patient orElseThrow = patientRepo.findByEmail(email)
				.orElseThrow(() -> new PatientNotFoundException("Patient Not found"));
		return mapper.responseMapper(orElseThrow);
	}

	@Override
	@Transactional
	public PatientResponse updatePatient(Long id, PatientRequest request) {

		Patient patient = patientRepo.findById(id).orElseThrow(() -> new PatientNotFoundException("Patient Not found"));

		mapper.updatePatientFromRequest(patient, request);

		Patient updatedPatient = patientRepo.save(patient);
		return mapper.responseMapper(updatedPatient);

	}

	@Override
	public void deletePatient(Long id) {
		Patient patient = patientRepo.findById(id).orElseThrow(() -> new PatientNotFoundException("Patient not found"));

		if (!patient.isActive()) {
			throw new PatientNotFoundException("Patient is deleted");
		}

		patient.setActive(false);

		patientRepo.save(patient);

	}

	@Override
	public List<PatientResponse> getAllPatients() {
		 List<Patient> allPatients = patientRepo.findAll();
		List<PatientResponse> allPatientsList = allPatients.stream().map(mapper:: responseMapper).toList();
		 return allPatientsList;
	}

	
}
