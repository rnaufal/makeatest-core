package com.yediat.makeatest.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.yediat.makeatest.annotations.FailType;
import com.yediat.makeatest.annotations.loadannotation.LoadAnnotation;
import com.yediat.makeatest.core.MakeATestController;
import com.yediat.makeatest.core.MakeATestException;
import com.yediat.makeatest.core.MakeATestInitializationException;


public class LoadVariableInObjectTest {

	@LoadAnnotation(value="Set this message in variable message",withProcessor=true,failType=FailType.NONE)
	public String message;
	
	@SuppressWarnings("unused")
	@Test
	public void shouldLoadVariableInObject() throws MakeATestInitializationException, MakeATestException{
		MakeATestController makeATestController = new MakeATestController(this);
		assertEquals("Set this message in variable message", this.message);
	}
	
}
