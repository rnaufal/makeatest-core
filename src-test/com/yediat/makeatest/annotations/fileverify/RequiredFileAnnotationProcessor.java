package com.yediat.makeatest.annotations.fileverify;

import java.io.File;
import java.io.IOException;

import com.yediat.makeatest.core.MakeATestAssertionError;
import com.yediat.makeatest.core.metadata.processor.AnnotationProcessor;

/**
 * Classe responsável pelo processamento da anotação
 * {@link RequiredFileAnnotation}. O objetivo é configurar o ambiente, criando o
 * arquivo requerido (indicado pela anotação) antes da execução do teste.
 * 
 * @author Debora Chama
 * 
 */

public class RequiredFileAnnotationProcessor extends AnnotationProcessor {

	private String filePath;

	public RequiredFileAnnotationProcessor(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public void process(Object instance) throws MakeATestAssertionError {
		File f = new File(this.filePath);
		try {
			f.createNewFile();
		} catch (IOException e) {
			throw new MakeATestAssertionError(e.getMessage());
		}
	}

}
