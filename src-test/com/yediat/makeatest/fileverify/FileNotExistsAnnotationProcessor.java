package com.yediat.makeatest.fileverify;

import java.io.File;

import com.yediat.makeatest.core.MakeATestAssertionError;
import com.yediat.makeatest.core.metadata.processor.MetadataProcessor;

public class FileNotExistsAnnotationProcessor implements MetadataProcessor {

	private String filePath;
	
	public FileNotExistsAnnotationProcessor(String filePath) {
		this.filePath = filePath;
	}
	
	@Override
	public void after() throws MakeATestAssertionError {
		File f = new File(filePath);
		if (f.exists()) {
			throw new MakeATestAssertionError("File exists in " + filePath);
		}
	}

	@Override
	public void both() throws MakeATestAssertionError {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void before() throws MakeATestAssertionError {
		// TODO Auto-generated method stub
		
	}

}
