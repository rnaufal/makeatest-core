package com.yediat.makeatest.annotations.proxymethod;

import com.yediat.makeatest.annotations.FailType;
import com.yediat.makeatest.annotations.proxymethod.annotation.ProxyMethodBeforeAnnotation;

public class ProxyMethodBefore {

	public String message = "Init the method";
	
	@ProxyMethodBeforeAnnotation(failType = FailType.NONE, text = "Init the method", variable="message")
	public void beforeVerifyMessage(){
		this.message = "Into the method";
	}
}
