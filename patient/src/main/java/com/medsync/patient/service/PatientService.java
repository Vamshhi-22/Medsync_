package com.medsync.patient.service;

import java.util.List;

import com.medsync.patient.playload.request.PatientRequest;
import com.medsync.patient.playload.response.PatientResponse;

public interface PatientService {

	PatientResponse createPatient(PatientRequest request);

	PatientResponse findById(Long id);

	PatientResponse getPatientByEmail(String email);

	PatientResponse updatePatient(Long id, PatientRequest request);

	void deletePatient(Long id);

	List<PatientResponse> getAllPatients();

}
