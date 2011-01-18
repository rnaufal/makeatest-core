package com.yediat.makeatest.fileverify;

import com.yediat.makeatest.container.PropertyDescriptor;
import com.yediat.makeatest.metadatareading.delegate.AnnotationReader;

/**
 * Classe responsável pela leitura da anotação {@link RequiredFileAnnotation}. 
 * O objetivo é configurar o ambiente, criando o arquivo requerido (indicado pela anotação)
 * antes da execução do teste.
 * 
 * @author Debora Chama
 * 
 */

public class RequiredFileAnnotationReader implements AnnotationReader<RequiredFileAnnotation> {

	@Override
	public void readAnnotation(RequiredFileAnnotation annotation, PropertyDescriptor descriptor) {
		String path = annotation.filePath();		
		RequiredFileAnnotationProcessor processor = new RequiredFileAnnotationProcessor(path);
		descriptor.setProcessor(processor);
	}

}
