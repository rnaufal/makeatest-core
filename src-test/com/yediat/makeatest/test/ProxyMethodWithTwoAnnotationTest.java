package com.yediat.makeatest.test;


import junit.framework.Assert;

import org.junit.Test;

import com.yediat.makeatest.annotations.proxymethod.ProxyMethodWithTwoAnnotation;
import com.yediat.makeatest.core.MakeATestAssertionError;
import com.yediat.makeatest.core.MakeATestController;
import com.yediat.makeatest.core.MakeATestException;
import com.yediat.makeatest.core.MakeATestInitializationException;

public class ProxyMethodWithTwoAnnotationTest {

	public String message;

	@Test
	public void shouldExecuteTwoAnnotationAndFail() throws MakeATestInitializationException, MakeATestException {
		MakeATestController makeATestController = new MakeATestController(new ProxyMethodWithTwoAnnotation());
		ProxyMethodWithTwoAnnotation proxyMethod = (ProxyMethodWithTwoAnnotation) makeATestController.getObjectInstanceProxy();
		try {
			proxyMethod.shouldExecuteTwoAnnotation();
		} catch (MakeATestAssertionError e) {
			System.out.println(e.getMessage());
			if(!e.getMessage().equals("ProxyMethodWithTwoAnnotation Assert fail.")){
				Assert.assertTrue(false);
			} else {
				Assert.assertTrue(true);
			}
		}
	}

}
