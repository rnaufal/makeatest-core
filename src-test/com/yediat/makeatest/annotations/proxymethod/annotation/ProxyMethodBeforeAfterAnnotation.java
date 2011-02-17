package com.yediat.makeatest.annotations.proxymethod.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.yediat.makeatest.annotations.FailType;
import com.yediat.makeatest.core.metadata.reading.MakeATestProxyBehavior;
import com.yediat.makeatest.core.metadata.reading.MakeATestReader;
import com.yediat.makeatest.core.metadata.reading.MakeATestScope;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@MakeATestReader(reader=ProxyMethodBeforeAfterAnnotationReader.class,proxyBehavior={MakeATestProxyBehavior.BEFORE, MakeATestProxyBehavior.AFTER},scope=MakeATestScope.PROXYMETHOD)
public @interface ProxyMethodBeforeAfterAnnotation {
	String textBefore();
	String textAfter();
	String variable();
	FailType failType();
}
