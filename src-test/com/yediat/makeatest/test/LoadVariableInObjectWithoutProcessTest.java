package com.yediat.makeatest.test;


import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.yediat.makeatest.annotations.FailType;
import com.yediat.makeatest.annotations.loadannotation.LoadAnnotation;
import com.yediat.makeatest.core.MakeATestAssertionError;
import com.yediat.makeatest.core.MakeATestController;
import com.yediat.makeatest.core.MakeATestException;
import com.yediat.makeatest.core.MakeATestInitializationException;

public class LoadVariableInObjectWithoutProcessTest {

	@LoadAnnotation(value="Set this message in variable message",withProcessor=false,failType=FailType.NONE)
	public String message;
	
	@SuppressWarnings("unused")
	@Test
	public void shouldExceptionMakeATestAssertError() throws MakeATestInitializationException, MakeATestException {
		try {
			MakeATestController makeATestController = new MakeATestController(this);
		} catch (MakeATestAssertionError e) {
			String message = e.getMessage().replaceAll("\\n", "");
			assertTrue(message.matches(".*Processor not implemented for annotaion:.*com.yediat.makeatest.annotations.loadannotation.LoadAnnotation.*"));
		}
	}
}
