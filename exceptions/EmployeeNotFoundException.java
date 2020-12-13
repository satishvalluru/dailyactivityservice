package com.java.exceptions;

/**
 * 
 * @author santo
 *
 */
public class EmployeeNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public EmployeeNotFoundException(String message) {
		super(message);
	}

	public EmployeeNotFoundException(String message, Throwable t) {
		super(message, t);
	}
}
