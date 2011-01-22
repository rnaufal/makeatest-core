package com.yediat.makeatest.core;

public class MakeATestAssertionError extends AssertionError {
	
	private static final long serialVersionUID = 1L;

	public MakeATestAssertionError() {
	}

	public MakeATestAssertionError(String message) {
		super(message);
	}
	
	public MakeATestAssertionError(Exception e) {
		super(e);
	}

}
