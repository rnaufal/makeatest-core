package com.yediat.makeatest.test;


import org.junit.Test;

import com.yediat.makeatest.annotations.proxymethod.ProxyMethodBefore;
import com.yediat.makeatest.annotations.proxymethod.ProxyMethodBeforeExceptionInProcessor;
import com.yediat.makeatest.annotations.proxymethod.ProxyMethodBeforeExceptionInReader;
import com.yediat.makeatest.core.MakeATestController;
import com.yediat.makeatest.core.MakeATestException;
import com.yediat.makeatest.core.MakeATestInitializationException;

public class ProxyMethodBeforeTest {
	
	@Test
	public void shoudValidateBeforeMessage() throws MakeATestInitializationException, MakeATestException{
		MakeATestController makeATestController = new MakeATestController(new ProxyMethodBefore());
		ProxyMethodBefore proxyMethodBefore = (ProxyMethodBefore) makeATestController.getObjectInstanceProxy();
		proxyMethodBefore.beforeVerifyMessage();
	}
	
	@Test(expected=MakeATestInitializationException.class)
	public void shouldValidateExceptionInReader() throws MakeATestInitializationException, MakeATestException {
		MakeATestController makeATestController = new MakeATestController(new ProxyMethodBeforeExceptionInReader());
		ProxyMethodBeforeExceptionInReader proxyMethodBeforeExceptionInReader = (ProxyMethodBeforeExceptionInReader) makeATestController.getObjectInstanceProxy();
		proxyMethodBeforeExceptionInReader.beforeVerifyExceptoinInReader();		
	}

	@Test(expected=MakeATestException.class)
	public void shouldValidateExceptionInProcessor() throws MakeATestInitializationException, MakeATestException {
		MakeATestController makeATestController = new MakeATestController(new ProxyMethodBeforeExceptionInProcessor());
		ProxyMethodBeforeExceptionInProcessor proxyMethodBeforeExceptionInProcessor = (ProxyMethodBeforeExceptionInProcessor) makeATestController.getObjectInstanceProxy();
		proxyMethodBeforeExceptionInProcessor.beforeVerifyExceptionInReader();
	}

}
