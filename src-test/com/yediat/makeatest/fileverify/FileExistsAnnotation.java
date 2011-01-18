package com.yediat.makeatest.fileverify;

import static java.lang.annotation.ElementType.METHOD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.yediat.makeatest.annotations.MakeATestConfig;
import com.yediat.makeatest.metadatareading.delegate.DelegateReader;

/**
 * Anotação para o Make a Test verificar se o arquivo existe no local indicado
 * @author Marcus Floriano
 *
 */

@MakeATestConfig(klass = FileExistsAnnotationExecute.class)
@Target({METHOD})
@Retention(RetentionPolicy.RUNTIME)
@DelegateReader(FileExistsAnnotationExecute.class)
public @interface FileExistsAnnotation {
	String filePath();
}
