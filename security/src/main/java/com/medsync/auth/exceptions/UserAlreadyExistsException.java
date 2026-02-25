package com.medsync.auth.exceptions;

public class UserAlreadyExistsException extends RuntimeException {

	public UserAlreadyExistsException(String message)
	{
		super(message);
	}
}
