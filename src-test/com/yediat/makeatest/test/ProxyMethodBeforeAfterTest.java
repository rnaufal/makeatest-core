package com.yediat.makeatest.test;

import org.junit.Test;

import com.yediat.makeatest.annotations.proxymethod.ProxyMethodBeforeAfter;
import com.yediat.makeatest.core.MakeATestController;
import com.yediat.makeatest.core.MakeATestException;
import com.yediat.makeatest.core.MakeATestInitializationException;


public class ProxyMethodBeforeAfterTest {

	@Test
	public void shouldValidateBeforeAndAfter() throws MakeATestInitializationException, MakeATestException {
		MakeATestController makeATestController = new MakeATestController(new ProxyMethodBeforeAfter());
		ProxyMethodBeforeAfter proxyMethodBeforeAfter = (ProxyMethodBeforeAfter) makeATestController.getObjectInstanceProxy();
		proxyMethodBeforeAfter.beforeAfterVerifyMessage();
	}
	
}
