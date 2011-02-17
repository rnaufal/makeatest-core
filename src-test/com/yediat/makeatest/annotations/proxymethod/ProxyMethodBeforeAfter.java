package com.yediat.makeatest.annotations.proxymethod;

import com.yediat.makeatest.annotations.FailType;
import com.yediat.makeatest.annotations.proxymethod.annotation.ProxyMethodBeforeAfterAnnotation;

public class ProxyMethodBeforeAfter {
	public String message = "Init the method";
	
	@ProxyMethodBeforeAfterAnnotation(failType = FailType.NONE, textBefore = "Init the method", textAfter="Into the method", variable="message")
	public void beforeAfterVerifyMessage(){
		this.message = "Into the method";
	}

}
