package com.medsync.auth.playload.response;

public class JwtResponse {

	private String token;
	
	private String type= "Bearer";
	
	private String userName;

	public JwtResponse(String token, String userName) {
		super();
		this.token = token;
		this.userName = userName;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
