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
	public void shouldAssertExceptionInvokeErrorWithJUnitAssert() throws MakeATestInitializationException, MakeATestException {
		MakeATestController controller = new MakeATestController(new ProxyMethodInvokeErrorWithAssert());
		ProxyMethodInvokeErrorWithAssert invoke = (ProxyMethodInvokeErrorWithAssert) controller.getObjectInstanceProxy();
		try {
			invoke.invokeErroWithJUniFrameworkAssert();
		} catch (MakeATestAssertionError e) {
			Assert.assertEquals("ProxyMethodInvokeErrorWithAssert fail null expected:<invoke [error]> but was:<invoke [fail]>", e.getMessage());
		}
	}
	
	@Test
	public void shouldAssertExceptionInvokeErrorWithJavaLangAssert() throws MakeATestInitializationException, MakeATestException {
		MakeATestController controller = new MakeATestController(new ProxyMethodInvokeErrorWithAssert());
		ProxyMethodInvokeErrorWithAssert invoke = (ProxyMethodInvokeErrorWithAssert) controller.getObjectInstanceProxy();
		try {
			invoke.invokeErroWithJavaLangAssert();
		} catch (MakeATestAssertionError e) {
			Assert.assertEquals("ProxyMethodInvokeErrorWithAssert fail error in invokeErroWithJavaLangAssert", e.getMessage());
		}
	}

	@Test
	public void shouldAssertExceptionInvokeErrorWithInvoctaionTargetException() throws MakeATestInitializationException, MakeATestException {
		MakeATestController controller = new MakeATestController(new ProxyMethodInvokeErrorWithAssert());
		ProxyMethodInvokeErrorWithAssert invoke = (ProxyMethodInvokeErrorWithAssert) controller.getObjectInstanceProxy();
		try {
			invoke.invocationTargetException();
		} catch (Exception e) {
			Assert.assertEquals("Exception when invocation object ProxyMethodInvokeErrorWithAssert, the exception: Assert Error invocationTargetException", e.getMessage());
			Assert.assertEquals(MakeATestException.class, e.getClass());
		}
	}

}
