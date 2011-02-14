package com.yediat.makeatest.annotations.fileverify;

import static java.lang.annotation.ElementType.METHOD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.yediat.makeatest.core.metadata.reading.MakeATestActionEnum;
import com.yediat.makeatest.core.metadata.reading.MakeATestReader;
import com.yediat.makeatest.core.metadata.reading.MakeATestScopeEnum;

/**
 * Anotação para o Make a Test que verifica a não existencia do arquivo.
 * Utilizado para validar uma rotina que remoção de arquivos.
 * @author marcusfloriano
 * 
 */

@Target({METHOD})
@Retention(RetentionPolicy.RUNTIME)
@MakeATestReader(value=FileNotExistsAnnotationReader.class,actions={MakeATestActionEnum.AFTER},scope=MakeATestScopeEnum.EXECUTE)
public @interface FileNotExistsAnnotation {
	String filePath();
}
