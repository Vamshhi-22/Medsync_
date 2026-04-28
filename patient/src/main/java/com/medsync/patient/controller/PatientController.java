package com.medsync.patient.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.medsync.patient.playload.request.PatientRequest; 
import com.medsync.patient.playload.response.PatientResponse;
import com.medsync.patient.service.PatientService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/patient")
public class PatientController {

	private final PatientService patientService;

	public PatientController(PatientService patientService) {

		this.patientService = patientService;
	}

	@PostMapping("/register")
	public ResponseEntity<PatientResponse> registerPatient(@Valid @RequestBody PatientRequest patientRequest) {
		PatientResponse patient = patientService.createPatient(patientRequest);

		return new ResponseEntity<PatientResponse>(patient, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<PatientResponse> getPatientById(@PathVariable Long id) {
		PatientResponse byId = patientService.findById(id);
		return ResponseEntity.ok(byId);
	}

	@GetMapping("/search")
	public ResponseEntity<PatientResponse> findByEmail(@RequestParam String email) {
		PatientResponse patientByEmail = patientService.getPatientByEmail(email);
		return ResponseEntity.ok(patientByEmail);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<PatientResponse> updatePatient(@PathVariable Long id, PatientRequest request) {
		PatientResponse updatedPatient = patientService.updatePatient(id, request);
		return ResponseEntity.ok(updatedPatient);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deletePatient(@PathVariable Long id) {
		patientService.deletePatient(id);
		return ResponseEntity.ok("Patirnt deleted succesfully");
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<PatientResponse>> getAllPatients()
	{
		return ResponseEntity.ok(patientService.getAllPatients());
	}
	
}
