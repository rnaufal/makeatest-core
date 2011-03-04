package com.yediat.makeatest.annotations.loadannotation;

import java.lang.reflect.Field;

import com.yediat.makeatest.annotations.FailType;
import com.yediat.makeatest.core.MakeATestAssertionError;
import com.yediat.makeatest.core.metadata.processor.AnnotationProcessor;

public class LoadAnnotationProcessor extends AnnotationProcessor {

	private Field annotated;
	private String value;
	private FailType failType;
	
	public LoadAnnotationProcessor(Field annotated, String value, FailType failType) {
		this.annotated = annotated;
		this.value = value;
		this.failType = failType;
	}
	
	@Override
	public void process(Object instance) throws MakeATestAssertionError {
		if(this.failType.equals(FailType.PROCESSOR)){
			new Integer("a");
		}
		try {
			this.annotated.set(instance, this.value);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

}
