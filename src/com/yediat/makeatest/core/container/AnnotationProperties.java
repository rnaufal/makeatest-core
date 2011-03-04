package com.yediat.makeatest.core.container;

import java.lang.annotation.Annotation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yediat.makeatest.core.MakeATestAssertionError;
import com.yediat.makeatest.core.metadata.processor.AnnotationProcessor;
import com.yediat.makeatest.core.metadata.reading.MakeATestProxyBehavior;

/**
 * Utilizado para armazenas a anotação que foi recuperada com o método a ser
 * executado e a classe executora referente a esse método.
 * 
 * @author deborachama
 * 
 */
public class AnnotationProperties {

	final Logger logger = LoggerFactory.getLogger(AnnotationProperties.class);
	
	private Annotation annotation;
	public Annotation getAnnotation() {
		return annotation;
	}

	public void setAnnotation(Annotation annotation) {
		this.annotation = annotation;
	}

	private AnnotationProcessor processor;
	private MakeATestProxyBehavior [] actions;
	private Object annotated;

	public Object getAnnotated() {
		return annotated;
	}

	public void setAnnotated(Object annotated) {
		this.annotated = annotated;
	}

	public AnnotationProcessor getProcessor() {
		if (processor == null) {
			processor = new AnnotationProcessor() {
				@Override
				public void process(Object instance) throws MakeATestAssertionError {
					throw new MakeATestAssertionError("Processor not implemented for annotaion: \n" + annotation.annotationType().getName());
				}
			};
		}
		return processor;
	}
	
	public void setProcessor(AnnotationProcessor processor) {
		this.processor = processor;
	}

	public MakeATestProxyBehavior [] getActions() {
		return actions;
	}

	public void setProxyBehabior(MakeATestProxyBehavior [] actions) {
		this.actions = actions;
	}

}
