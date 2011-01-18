package com.yediat.makeatest.fileverify;

import static java.lang.annotation.ElementType.METHOD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.yediat.makeatest.annotations.MakeATestConfig;
import com.yediat.makeatest.metadatareading.delegate.DelegateReader;

/**
 * Anotação para o Make a Test que indica o path de um arquivo requerido
 * que deve existir nesse local ANTES da execução do teste.
 * @author Debora Chama
 *
 */

@MakeATestConfig(klass = RequiredFileAnnotationReader.class)
@Target({METHOD})
@Retention(RetentionPolicy.RUNTIME)
@DelegateReader(RequiredFileAnnotationReader.class)
public @interface RequiredFileAnnotation {
	String filePath();
}
