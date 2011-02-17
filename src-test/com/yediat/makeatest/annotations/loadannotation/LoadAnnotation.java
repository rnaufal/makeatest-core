package com.yediat.makeatest.annotations.loadannotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.yediat.makeatest.annotations.FailType;
import com.yediat.makeatest.core.metadata.reading.MakeATestProxyBehavior;
import com.yediat.makeatest.core.metadata.reading.MakeATestReader;
import com.yediat.makeatest.core.metadata.reading.MakeATestScope;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@MakeATestReader(reader=LoadAnnotationReader.class,proxyBehavior={MakeATestProxyBehavior.BEFORE},scope=MakeATestScope.LOAD)
public @interface LoadAnnotation {
	String value();
	boolean withProcessor();
	FailType failType();
}
