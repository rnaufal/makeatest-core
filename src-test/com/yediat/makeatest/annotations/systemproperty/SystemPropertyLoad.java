package com.yediat.makeatest.annotations.systemproperty;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.yediat.makeatest.core.metadata.reading.MakeATestExecution;
import com.yediat.makeatest.core.metadata.reading.MakeATestExecutionEnum;
import com.yediat.makeatest.core.metadata.reading.MakeATestReader;
import com.yediat.makeatest.core.metadata.reading.MakeATestScope;
import com.yediat.makeatest.core.metadata.reading.MakeATestScopeEnum;

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@MakeATestReader(SystemPropertyLoadReader.class)
@MakeATestExecution(MakeATestExecutionEnum.BEFORE)
@MakeATestScope(MakeATestScopeEnum.LOAD)
public @interface SystemPropertyLoad {
	String key();
	String value();
}
