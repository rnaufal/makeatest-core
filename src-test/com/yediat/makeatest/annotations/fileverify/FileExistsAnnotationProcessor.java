package com.yediat.makeatest.annotations.fileverify;

import java.io.File;

import com.yediat.makeatest.core.MakeATestAssertionError;
import com.yediat.makeatest.core.metadata.processor.MetadataProcessor;

public class FileExistsAnnotationProcessor extends MetadataProcessor {

	private String filePath;
	
	public FileExistsAnnotationProcessor(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public void process(Object instance) throws MakeATestAssertionError {
		File f = new File(filePath);
		if (!f.exists()) {
			throw new MakeATestAssertionError("Arquivo não existe.");
		}
	}
	

}
