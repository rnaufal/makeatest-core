package com.yediat.makeatest.annotations.objectload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.yediat.makeatest.core.metadata.reading.MakeATestActionEnum;
import com.yediat.makeatest.core.metadata.reading.MakeATestReader;
import com.yediat.makeatest.core.metadata.reading.MakeATestScopeEnum;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@MakeATestReader(value=ObjectLoadReader.class,actions={MakeATestActionEnum.BEFORE},scope=MakeATestScopeEnum.LOAD)
public @interface ObjectLoad {
	String value();
}
