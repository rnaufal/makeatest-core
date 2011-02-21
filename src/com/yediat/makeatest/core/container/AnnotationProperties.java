package com.yediat.makeatest.core.container;

import java.lang.annotation.Annotation;

import com.yediat.makeatest.core.MakeATestAssertionError;
import com.yediat.makeatest.core.metadata.processor.MetadataProcessor;
import com.yediat.makeatest.core.metadata.reading.MakeATestProxyBehavior;

/**
 * Utilizado para armazenas a anotação que foi recuperada com o método a ser
 * executado e a classe executora referente a esse método.
 * 
 * @author deborachama
 * 
 */
public class AnnotationProperties {

	private Annotation annotation;
	public Annotation getAnnotation() {
		return annotation;
	}

	public void setAnnotation(Annotation annotation) {
		this.annotation = annotation;
	}

	private MetadataProcessor processor;
	private MakeATestProxyBehavior [] actions;
	private Object annotated;

	public Object getAnnotated() {
		return annotated;
	}

	public void setAnnotated(Object annotated) {
		this.annotated = annotated;
	}

	public MetadataProcessor getProcessor() {
		if (processor == null) {
			processor = new MetadataProcessor() {
				@Override
				public void process(Object instance) throws MakeATestAssertionError {
					throw new MakeATestAssertionError("Processor not implemented for annotaion: \n" + annotation.annotationType().getName());
				}
			};
		}
		return processor;
	}
	
	public void setProcessor(MetadataProcessor processor) {
		this.processor = processor;
	}

	public MakeATestProxyBehavior [] getActions() {
		return actions;
	}

	public void setProxyBehabior(MakeATestProxyBehavior [] actions) {
		this.actions = actions;
	}

}
