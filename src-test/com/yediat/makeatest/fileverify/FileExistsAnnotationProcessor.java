package com.yediat.makeatest.fileverify;

import java.io.File;
import java.io.FileNotFoundException;

import com.yediat.makeatest.core.metadata.processor.MetadataProcessor;

public class FileExistsAnnotationProcessor implements MetadataProcessor {

	private String filePath;
	
	public FileExistsAnnotationProcessor(String filePath) {
		this.filePath = filePath;
	}
	
	@Override
	public void after() throws Exception {
		File f = new File(filePath);
		if (!f.exists()) {
			throw new FileNotFoundException();
		}
	}

	@Override
	public void both() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void before() throws Exception {
		// TODO Auto-generated method stub
		
	}

}
