package com.yediat.makeatest.annotations.proxymethod;

import com.yediat.makeatest.annotations.FailType;
import com.yediat.makeatest.annotations.proxymethod.annotation.ProxyMethodBeforeAnnotation;

public class ProxyMethodBeforeExceptionInReader {

	public String message = "Init the method";
	
	@ProxyMethodBeforeAnnotation(failType = FailType.READER, text = "Init the method", variable="message")
	public void beforeVerifyExceptoinInReader(){
		this.message = "In to the method";
	}
	
}
