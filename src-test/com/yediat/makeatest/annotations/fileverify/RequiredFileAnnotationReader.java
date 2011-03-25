package com.yediat.makeatest.annotations.fileverify;

import com.yediat.makeatest.core.container.AnnotationProperties;
import com.yediat.makeatest.core.metadata.reading.MakeATestReaderInterface;

/**
 * Classe responsável pela leitura da anotação {@link RequiredFileAnnotation}. 
 * O objetivo é configurar o ambiente, criando o arquivo requerido (indicado pela anotação)
 * antes da execução do teste.
 * 
 * @author Debora Chama
 * 
 */

public class RequiredFileAnnotationReader implements MakeATestReaderInterface<RequiredFileAnnotation> {

	@Override
	public void readAnnotation(RequiredFileAnnotation annotation, AnnotationProperties annotationProperties) {
		String path = annotation.filePath();		
		RequiredFileAnnotationProcessor processor = new RequiredFileAnnotationProcessor(path);
		annotationProperties.setProcessor(processor);
	}

}
