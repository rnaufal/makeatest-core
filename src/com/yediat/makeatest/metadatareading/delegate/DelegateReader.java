package com.yediat.makeatest.metadatareading.delegate;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DelegateReader {	
	@SuppressWarnings("rawtypes")
	Class<? extends AnnotationReader> value();
}
