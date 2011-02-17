package com.yediat.makeatest.test;


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
	@Test(expected=MakeATestAssertionError.class)
	public void shouldExceptionMakeATestAssertError() throws MakeATestInitializationException, MakeATestException{
		MakeATestController makeATestController = new MakeATestController(this);
	}

}
