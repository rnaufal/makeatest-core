package com.yediat.makeatest.core;


public class MakeATestInitializationException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public MakeATestInitializationException(String message) {
		super(message);
	}
	
	public MakeATestInitializationException(String message, Exception e) {
		super(message, e);
	}

}
