package com.jwt.jwtToken;

import lombok.Data;

@Data
public class JwtResponse {

	public JwtResponse(String token) {
		super();
		this.token = token;
	}

	private String token;
}
