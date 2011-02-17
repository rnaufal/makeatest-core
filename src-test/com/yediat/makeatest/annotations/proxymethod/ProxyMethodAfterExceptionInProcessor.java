package com.yediat.makeatest.annotations.proxymethod;

import com.yediat.makeatest.annotations.FailType;
import com.yediat.makeatest.annotations.proxymethod.annotation.ProxyMethodAfterAnnotation;

public class ProxyMethodAfterExceptionInProcessor {
	public String message = "Init the method";

	@ProxyMethodAfterAnnotation(failType = FailType.PROCESSOR, text = "Into the method", variable = "message")
	public void afterVerifyMessage() {
		this.message = "Into the method";
	}
}
