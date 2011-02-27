package com.yediat.makeatest.annotations.proxymethod;

import junit.framework.Assert;

import com.yediat.makeatest.annotations.FailType;
import com.yediat.makeatest.annotations.proxymethod.annotation.ProxyMethodAfterAnnotation;
import com.yediat.makeatest.test.AssertionErrorTest;

public class ProxyMethodInvokeErrorWithAssert {

	public String message;
	
	@ProxyMethodAfterAnnotation(failType = FailType.NONE, text = "invoke error", variable = "message")
	public void invokeErroWithJUniFrameworkAssert() {
		Assert.assertEquals("invoke error", "invoke fail");
	}
	
	@ProxyMethodAfterAnnotation(failType = FailType.NONE, text = "invoke error", variable = "message")
	public void invokeErroWithJavaLangAssert() {
		throw new AssertionErrorTest("error in invokeErroWithJavaLangAssert");
	}

	@ProxyMethodAfterAnnotation(failType = FailType.NONE, text = "invoke error", variable = "message")
	public void invocationTargetException() {
		throw new AssertionError("Assert Error invocationTargetException");
	}

}
