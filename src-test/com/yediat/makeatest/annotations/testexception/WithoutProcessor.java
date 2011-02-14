package com.yediat.makeatest.annotations.testexception;

import static java.lang.annotation.ElementType.METHOD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.yediat.makeatest.core.metadata.reading.MakeATestActionEnum;
import com.yediat.makeatest.core.metadata.reading.MakeATestReader;
import com.yediat.makeatest.core.metadata.reading.MakeATestScopeEnum;

@Target({METHOD})
@Retention(RetentionPolicy.RUNTIME)
@MakeATestReader(value=WithoutProcessorReader.class,actions={MakeATestActionEnum.AFTER},scope=MakeATestScopeEnum.EXECUTE)
public @interface WithoutProcessor {

}
