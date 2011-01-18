package com.yediat.makeatest.fileverify;

import java.io.File;
import java.io.IOException;

import com.yediat.makeatest.metadataprocessor.MetadataProcessor;

/**
 * Classe responsável pelo processamento da anotação
 * {@link RequiredFileAnnotation}. O objetivo é configurar o ambiente, criando o
 * arquivo requerido (indicado pela anotação) antes da execução do teste.
 * 
 * @author Debora Chama
 * 
 */

public class RequiredFileAnnotationProcessor implements MetadataProcessor {

	private String filePath;

	public RequiredFileAnnotationProcessor(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public void process() throws Exception {
		File f = new File(this.filePath);
		try {
			f.createNewFile();
		} catch (IOException e) {
			throw e;
		}

	}

}
