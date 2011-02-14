package com.yediat.makeatest.core.metadata.reading;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Anotação para ser utilizada em anotações criadas pelo Make a Test.
 * Essa anotação indica qual a classe que será responsável pela leitura (reader) da anotação.
 * 
 * @author marcusfloriano
 *
 */

@Target({ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MakeATestReader {	
	@SuppressWarnings("rawtypes")
	Class<? extends MakeATestReaderInterface> value();
	MakeATestActionEnum [] actions();
	MakeATestScopeEnum scope();
}
