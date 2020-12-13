package com.java.exceptions;

/**
 * 
 * @author santo
 *
 */
public class DailyActivityNotFoundException extends Exception {

	private static final long serialVersionUID = 1L;

	public DailyActivityNotFoundException(String message) {
		super(message);
	}

	public DailyActivityNotFoundException(String message, Throwable t) {
		super(message, t);
	}
}
