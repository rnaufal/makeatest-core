package com.yediat.makeatest.annotations.testexception;

import static java.lang.annotation.ElementType.METHOD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.yediat.makeatest.annotations.FailType;
import com.yediat.makeatest.core.metadata.reading.MakeATestProxyBehavior;
import com.yediat.makeatest.core.metadata.reading.MakeATestReader;
import com.yediat.makeatest.core.metadata.reading.MakeATestScope;

/**
 * Anotação para o Make a Test verificar se o arquivo existe no local indicado
 * @author Marcus Floriano
 *
 */

@Target({METHOD})
@Retention(RetentionPolicy.RUNTIME)
@MakeATestReader(reader=FileExistsExceptionAnnotationReader.class,proxyBehavior={MakeATestProxyBehavior.AFTER},scope=MakeATestScope.PROXYMETHOD)
public @interface FileExistsExceptionAnnotation {
	String filePath();
	FailType failType();
}
