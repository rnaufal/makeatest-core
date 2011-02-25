package com.yediat.makeatest.annotations.proxymethod;

import junit.framework.Assert;

import com.yediat.makeatest.annotations.FailType;
import com.yediat.makeatest.annotations.proxymethod.annotation.ProxyMethodAfterAnnotation;

public class ProxyMethodInvokeErrorWithAssert {

	public String message;
	
	@ProxyMethodAfterAnnotation(failType = FailType.NONE, text = "invoke error", variable = "message")
	public void invokeErroWithAssert() {
		Assert.assertEquals("invoke error", "invoke fail");
	}
	
}
