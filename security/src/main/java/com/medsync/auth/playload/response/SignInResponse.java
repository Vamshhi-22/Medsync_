package com.medsync.auth.playload.response;

public class SignInResponse {

	private String userName;
	
	private String message;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public SignInResponse(String userName, String message) {
		super();
		this.userName = userName;
		this.message = message;
	}
	
	
	
}
