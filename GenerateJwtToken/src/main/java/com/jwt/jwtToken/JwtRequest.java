package com.jwt.jwtToken;

import lombok.Data;

@Data
public class JwtRequest {

	private String userName;
	private String password;
	public JwtRequest(String userName, String password) {
		super();
		this.userName = userName;
		this.password = password;
	}
	public JwtRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
