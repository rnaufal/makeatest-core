package com.yediat.makeatest.test;

import org.junit.Test;

import com.yediat.makeatest.annotations.FailType;
import com.yediat.makeatest.annotations.loadannotation.LoadAnnotation;
import com.yediat.makeatest.core.MakeATestController;
import com.yediat.makeatest.core.MakeATestException;
import com.yediat.makeatest.core.MakeATestInitializationException;


public class LoadWithExceptionInReaderTest {
	@LoadAnnotation(value="Set this message in variable message",withProcessor=true,failType=FailType.READER)
	public String message;
	
	@SuppressWarnings("unused")
	@Test(expected=MakeATestInitializationException.class)
	public void shouldExceptionInProcessor() throws MakeATestInitializationException, MakeATestException{
		MakeATestController makeATestController = new MakeATestController(this);
	}
}
