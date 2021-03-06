package com.yediat.makeatest.annotations.proxymethod;

import com.yediat.makeatest.annotations.FailType;
import com.yediat.makeatest.annotations.proxymethod.annotation.ProxyMethodBeforeAnnotation;

public class ProxyMethodBeforeExceptionInProcessor {

	public String message = "Init the method";
	
	@ProxyMethodBeforeAnnotation(failType = FailType.PROCESSOR, text = "Init the method", variable="message")
	public void beforeVerifyExceptionInReader(){
		this.message = "In to the method";
	}

}
