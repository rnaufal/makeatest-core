package com.yediat.makeatest.annotations.fileverify;

import static java.lang.annotation.ElementType.METHOD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.yediat.makeatest.core.metadata.reading.MakeATestProxyBehavior;
import com.yediat.makeatest.core.metadata.reading.MakeATestReader;
import com.yediat.makeatest.core.metadata.reading.MakeATestScope;

/**
 * Anotação para o Make a Test que indica o path de um arquivo requerido
 * que deve existir nesse local ANTES da execução do teste.
 * @author Debora Chama
 *
 */

@Target({METHOD})
@Retention(RetentionPolicy.RUNTIME)
@MakeATestReader(reader=RequiredFileAnnotationReader.class,proxyBehavior={MakeATestProxyBehavior.BEFORE},scope=MakeATestScope.PROXYMETHOD)
public @interface RequiredFileAnnotation {
	String filePath();
}
