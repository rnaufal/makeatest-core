package com.yediat.makeatest.annotations.proxymethod;

import junit.framework.Assert;

import com.yediat.makeatest.annotations.FailType;
import com.yediat.makeatest.annotations.proxymethod.annotation.ProxyMethodAfterAnnotation;
import com.yediat.makeatest.annotations.proxymethod.annotation.ProxyMethodBeforeAnnotation;

public class ProxyMethodWithTwoAnnotation {

	public String message = "Before Execution";
	
	@ProxyMethodBeforeAnnotation(failType = FailType.NONE, text = "Before Execution", variable = "message")
	@ProxyMethodAfterAnnotation(failType = FailType.NONE, text = "After Execution", variable = "message")
	public void shouldExecuteTwoAnnotation() {
		this.message = "Afterrr Execution";
		Assert.assertTrue(false);
	}

	
}
