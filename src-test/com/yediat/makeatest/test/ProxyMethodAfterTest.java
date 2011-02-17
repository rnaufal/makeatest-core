package com.yediat.makeatest.test;


import org.junit.Test;

import com.yediat.makeatest.annotations.proxymethod.ProxyMethodAfter;
import com.yediat.makeatest.core.MakeATestController;
import com.yediat.makeatest.core.MakeATestException;
import com.yediat.makeatest.core.MakeATestInitializationException;

public class ProxyMethodAfterTest {

	@Test
	public void shoudValidateAfterMessage() throws MakeATestInitializationException, MakeATestException{
		MakeATestController makeATestController = new MakeATestController(new ProxyMethodAfter());
		ProxyMethodAfter proxyMethodAfter = (ProxyMethodAfter) makeATestController.getObjectInstanceProxy();
		proxyMethodAfter.afterVerifyMessage();
	}

}
