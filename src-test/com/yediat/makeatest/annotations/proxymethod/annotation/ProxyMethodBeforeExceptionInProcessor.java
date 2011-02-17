package com.yediat.makeatest.annotations.proxymethod.annotation;

import com.yediat.makeatest.annotations.FailType;

public class ProxyMethodBeforeExceptionInProcessor {

	public String message = "Init the method";
	
	@ProxyMethodBeforeAnnotation(failType = FailType.PROCESSOR, text = "Init the method", variable="message")
	public void beforeVerifyExceptoinInReader(){
		this.message = "In to the method";
	}

}
