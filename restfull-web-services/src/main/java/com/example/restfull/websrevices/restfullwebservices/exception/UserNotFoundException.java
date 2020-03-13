package com.example.restfull.websrevices.restfullwebservices.exception;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1451418006521415665L;

	public UserNotFoundException(String message) {
		super(message);
	}

}
