package com.yediat.makeatest.core;

public class MakeATestException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public MakeATestException(String message) {
		super(message);
	}
	
	public MakeATestException(String message, Throwable throwable) {
		super(message, throwable);
	}
	
	public MakeATestException(String message, Exception e) {
		super(message, e);
	}

}
