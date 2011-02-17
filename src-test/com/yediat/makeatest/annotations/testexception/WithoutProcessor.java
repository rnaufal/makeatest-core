package com.yediat.makeatest.annotations.testexception;

import static java.lang.annotation.ElementType.METHOD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.yediat.makeatest.core.metadata.reading.MakeATestProxyBehavior;
import com.yediat.makeatest.core.metadata.reading.MakeATestReader;
import com.yediat.makeatest.core.metadata.reading.MakeATestScope;

@Target({METHOD})
@Retention(RetentionPolicy.RUNTIME)
@MakeATestReader(reader=WithoutProcessorReader.class,proxyBehavior={MakeATestProxyBehavior.AFTER},scope=MakeATestScope.PROXYMETHOD)
public @interface WithoutProcessor {

}
