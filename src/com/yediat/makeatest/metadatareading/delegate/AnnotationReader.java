package com.yediat.makeatest.metadatareading.delegate;

import java.lang.annotation.Annotation;

import com.yediat.makeatest.container.PropertyDescriptor;

public interface AnnotationReader<A extends Annotation> {

	public void readAnnotation(A annotation, PropertyDescriptor descriptor);

}
