package com.yediat.makeatest.annotations.fileverify;

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
 * Anotação para o Make a Test que verifica a não existencia do arquivo.
 * Utilizado para validar uma rotina que remoção de arquivos.
 * @author marcusfloriano
 * 
 */

@Target({METHOD})
@Retention(RetentionPolicy.RUNTIME)
@MakeATestReader(FileNotExistsAnnotationReader.class)
@MakeATestExecution(MakeATestExecutionEnum.AFTER)
@MakeATestScope(MakeATestScopeEnum.EXECUTE)
public @interface FileNotExistsAnnotation {
	String filePath();
}
