package com.yediat.makeatest.fileverify;

import java.lang.reflect.Field;

import com.yediat.makeatest.core.MakeATestAssertionError;
import com.yediat.makeatest.core.metadata.processor.MetadataProcessor;

public class ObjectLoadProcessor implements MetadataProcessor {

	private Field annotated;
	private String value;
	
	public ObjectLoadProcessor(Field annotated, String value) {
		this.annotated = annotated;
		this.value = value;
	}
	
	@Override
	public void process(Object instance) throws MakeATestAssertionError {
		try {
			this.annotated.set(instance, this.value);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

}
