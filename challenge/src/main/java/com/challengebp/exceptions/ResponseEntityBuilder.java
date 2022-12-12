package com.challengebp.exceptions;

import org.springframework.http.ResponseEntity;

/**
 * @author David Mogrovejo
 */
public class ResponseEntityBuilder {
	
	public static ResponseEntity<Object> build(ApiError apiError) {
	      return new ResponseEntity<>(apiError, apiError.getStatus());
	}

}

