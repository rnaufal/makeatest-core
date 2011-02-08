package com.yediat.makeatest.systempropertyload;

import com.yediat.makeatest.core.MakeATestAssertionError;
import com.yediat.makeatest.core.metadata.processor.MetadataProcessor;

public class SystemPropertyLoadProcessor implements MetadataProcessor {

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
