package com.yediat.makeatest.annotations.testexception;

import static java.lang.annotation.ElementType.METHOD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.yediat.makeatest.core.metadata.reading.MakeATestExecution;
import com.yediat.makeatest.core.metadata.reading.MakeATestExecutionEnum;
import com.yediat.makeatest.core.metadata.reading.MakeATestReader;
import com.yediat.makeatest.core.metadata.reading.MakeATestScope;
import com.yediat.makeatest.core.metadata.reading.MakeATestScopeEnum;

/**
 * Anotação para o Make a Test verificar se o arquivo existe no local indicado
 * @author Marcus Floriano
 *
 */

@Target({METHOD})
@Retention(RetentionPolicy.RUNTIME)
@MakeATestReader(FileExistsExceptionAnnotationReader.class)
@MakeATestExecution(MakeATestExecutionEnum.AFTER)
@MakeATestScope(MakeATestScopeEnum.EXECUTE)
public @interface FileExistsExceptionAnnotation {
	String filePath();
	String failType();
}