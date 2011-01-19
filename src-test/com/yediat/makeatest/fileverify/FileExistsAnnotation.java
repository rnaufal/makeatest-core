package com.yediat.makeatest.fileverify;

import static java.lang.annotation.ElementType.METHOD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.yediat.makeatest.core.metadata.reading.MakeATestReader;

/**
 * Anotação para o Make a Test verificar se o arquivo existe no local indicado
 * @author Marcus Floriano
 *
 */

@Target({METHOD})
@Retention(RetentionPolicy.RUNTIME)
@MakeATestReader(FileExistsAnnotationReader.class)
public @interface FileExistsAnnotation {
	String filePath();
}
