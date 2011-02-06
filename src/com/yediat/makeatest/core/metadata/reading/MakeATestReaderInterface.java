package com.yediat.makeatest.core.metadata.reading;

import java.lang.annotation.Annotation;

import com.yediat.makeatest.core.container.AnnotationProperties;

/**
 * Interface a ser implementada para a leitura da anotação criada para o Make a Test
 * 
 * @author marcusfloriano
 *
 * @param <A> A interface tem que receber um objeto que extende a classe Annotation
 */
public interface MakeATestReaderInterface<A extends Annotation> {
	public void readAnnotation(A annotation, AnnotationProperties descriptor);
}
