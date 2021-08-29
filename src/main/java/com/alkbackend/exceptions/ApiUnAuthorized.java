package com.alkbackend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class ApiUnAuthorized extends Exception {

	private static final long serialVersionUID = 1L;

	public ApiUnAuthorized(String message) {
		super(message);
	}
}
