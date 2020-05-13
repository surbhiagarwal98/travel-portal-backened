package com.nagarro.tportal.dto;

/**
 * authentication response model class
 * 
 * @author surbhiagarwal
 *
 */
public class AuthenticationResponse {

	private final String jwt;

	public AuthenticationResponse(String jwt) {
		this.jwt = jwt;
	}

	public String getJwt() {
		return jwt;
	}

}
