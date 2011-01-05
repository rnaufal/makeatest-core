package com.yediat.makeatest.fileverify;

import static java.lang.annotation.ElementType.METHOD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.yediat.makeatest.annotations.MakeATestConfig;

/**
 * Anotação para o Make a Test que verifica a não existencia do arquivo.
 * Utilizado para validar uma rotina que remoção de arquivos.
 * @author marcusfloriano
 * 
 */

@MakeATestConfig(klass = FileNotExistsAnnotationExecute.class)
@Target({METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FileNotExistsAnnotation {
	String filePath();
}
