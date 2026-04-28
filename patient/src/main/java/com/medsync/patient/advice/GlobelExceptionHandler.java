package com.medsync.patient.advice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.medsync.patient.Exception.PatientNotFoundException;

@RestControllerAdvice
public class GlobelExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<Map<String, Object>> handlePatientNotFoundException(PatientNotFoundException ex) {
		Map<String, Object> body = new HashMap<String, Object>();

		body.put("timestamp", LocalDateTime.now());
		body.put("message", ex.getMessage());
		body.put("status", HttpStatus.NOT_FOUND.value());

		return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
	}
}
