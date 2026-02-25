package com.medsync.auth.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.medsync.auth.model.User;
import com.medsync.auth.playload.request.SignInRequest;
import com.medsync.auth.playload.request.SignUpRequest;
import com.medsync.auth.playload.response.JwtResponse;
import com.medsync.auth.playload.response.SignInResponse;
import com.medsync.auth.playload.response.SignUpResponse;
import com.medsync.auth.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private final AuthService authService;

	public AuthController(AuthService authService) {
		super();
		this.authService = authService;
	}

	@PostMapping("/signup")
	public ResponseEntity<SignUpResponse> registerUser(@RequestBody SignUpRequest signUpRequest) {
		User registerUser = authService.registerUser(signUpRequest);

		return ResponseEntity.ok(new SignUpResponse(registerUser.getUserName(), " signedup sucessfully"));

	}

	@PostMapping("/signin")
	public ResponseEntity<JwtResponse> loginUser(@RequestBody SignInRequest signInRequest) {
		JwtResponse authenticateUser = authService.authenticateUser(signInRequest);

		return ResponseEntity.ok(authenticateUser);
	}

}
