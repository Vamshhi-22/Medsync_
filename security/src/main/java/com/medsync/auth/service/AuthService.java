package com.medsync.auth.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.medsync.auth.exceptions.UserAlreadyExistsException;
import com.medsync.auth.model.User;
import com.medsync.auth.playload.request.SignInRequest;
import com.medsync.auth.playload.request.SignUpRequest;
import com.medsync.auth.playload.response.JwtResponse;
import com.medsync.auth.repository.UserRepository;
import com.medsync.auth.security.jwt.JwtUtils;

@Service
public class AuthService {

	private final UserRepository repository;

	private final PasswordEncoder encoder;

	private final AuthenticationManager authenticationManager;

	private final JwtUtils jwtUtils;

	public AuthService(UserRepository repository, PasswordEncoder encoder, JwtUtils jwtUtils,
			AuthenticationManager authenticationManager) {
		super();
		this.repository = repository;
		this.encoder = encoder;
		this.authenticationManager = authenticationManager;
		this.jwtUtils = jwtUtils;
	}

	@Transactional
	public User registerUser(SignUpRequest request) {
		if (repository.existsByUserName(request.getUserName())) {

			throw new UserAlreadyExistsException(request.getUserName() + " already exists");
		}
		User user = new User(request.getUserName(), request.getEmail(), encoder.encode(request.getPassword()));
		System.out.println("checking data " + user.getUserName());
		return repository.save(user);

	}

	@Transactional
	public JwtResponse authenticateUser(SignInRequest logInRequest) {

		Authentication authenticate = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(logInRequest.getUserName(), logInRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authenticate);

		String jwt = jwtUtils.genrateJwtToken(authenticate);

		return new JwtResponse(jwt, logInRequest.getUserName());
	}
}
