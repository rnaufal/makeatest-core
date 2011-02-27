package com.yediat.makeatest.test;

import junit.framework.Assert;

import org.junit.Test;

import com.yediat.makeatest.annotations.proxymethod.ProxyMethodInvokeErrorWithAssert;
import com.yediat.makeatest.core.MakeATestAssertionError;
import com.yediat.makeatest.core.MakeATestController;
import com.yediat.makeatest.core.MakeATestException;
import com.yediat.makeatest.core.MakeATestInitializationException;

public class ProxyMethodInvokeErrorWithAssertTest {

	@Test
	public void shouldAssertExceptionInvokeError() throws MakeATestInitializationException, MakeATestException {
		MakeATestController controller = new MakeATestController(new ProxyMethodInvokeErrorWithAssert());
		ProxyMethodInvokeErrorWithAssert invoke = (ProxyMethodInvokeErrorWithAssert) controller.getObjectInstanceProxy();
		try {
			invoke.invokeErroWithAssert();
		} catch (MakeATestAssertionError e) {
			Assert.assertEquals("ProxyMethodInvokeErrorWithAssert fail null expected:<invoke [error]> but was:<invoke [fail]>", e.getMessage());
		}
	}
}
