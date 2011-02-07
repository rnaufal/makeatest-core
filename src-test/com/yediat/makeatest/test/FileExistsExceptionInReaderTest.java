package com.yediat.makeatest.test;

import org.junit.Test;

import com.yediat.makeatest.ExceptionInReader;
import com.yediat.makeatest.core.MakeATestController;
import com.yediat.makeatest.core.MakeATestException;
import com.yediat.makeatest.core.MakeATestInitializationException;


public class FileExistsExceptionInReaderTest {
	
	@SuppressWarnings("unused")
	@Test(expected=MakeATestInitializationException.class)
	public void assertErrorInVerifyFileExists() throws MakeATestInitializationException, MakeATestException {
		ExceptionInReader exceptionInReader = new ExceptionInReader();
		MakeATestController makeATestController = new MakeATestController(exceptionInReader);
	}
	
}
