package com.yediat.makeatest.annotations.systemproperty;

import com.yediat.makeatest.core.MakeATestAssertionError;
import com.yediat.makeatest.core.metadata.processor.AnnotationProcessor;

public class SystemPropertyLoadProcessor extends AnnotationProcessor {

	private String key;
	private String value;
	
	public SystemPropertyLoadProcessor(String key, String value) {
		this.key = key;
		this.value = value;
	}
	
	@Override
	public void process(Object instance) throws MakeATestAssertionError {
		System.setProperty(key, value);
	}

}
