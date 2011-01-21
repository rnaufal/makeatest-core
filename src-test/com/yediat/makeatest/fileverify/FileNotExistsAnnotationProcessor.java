package com.yediat.makeatest.fileverify;

import java.io.File;

import com.yediat.makeatest.core.MakeATestAssertionError;
import com.yediat.makeatest.core.metadata.processor.MetadataProcessor;

public class FileNotExistsAnnotationProcessor extends MetadataProcessor {

	private String filePath;
	
	public FileNotExistsAnnotationProcessor(String filePath) {
		this.filePath = filePath;
	}
	
	@Override
	public void process() throws MakeATestAssertionError {
		File f = new File(filePath);
		if (f.exists()) {
			throw new MakeATestAssertionError("File exists in " + filePath);
		}
	}

}
