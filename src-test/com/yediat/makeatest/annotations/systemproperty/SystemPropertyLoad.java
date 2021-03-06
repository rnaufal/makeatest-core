package com.yediat.makeatest.annotations.systemproperty;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.yediat.makeatest.core.metadata.reading.MakeATestProxyBehavior;
import com.yediat.makeatest.core.metadata.reading.MakeATestReader;
import com.yediat.makeatest.core.metadata.reading.MakeATestScope;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@MakeATestReader(reader=SystemPropertyLoadReader.class,proxyBehavior={MakeATestProxyBehavior.BEFORE},scope=MakeATestScope.LOAD)
public @interface SystemPropertyLoad {
	String key();
	String value();
}
