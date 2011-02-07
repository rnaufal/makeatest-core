package com.yediat.makeatest.fileverify;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.yediat.makeatest.core.metadata.reading.MakeATestReader;
import com.yediat.makeatest.core.metadata.reading.MakeATestScope;
import com.yediat.makeatest.core.metadata.reading.MakeATestScopeEnum;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@MakeATestReader(ObjectLoadReader.class)
@MakeATestScope(MakeATestScopeEnum.LOAD)
public @interface ObjectLoad {
	String value();
}
