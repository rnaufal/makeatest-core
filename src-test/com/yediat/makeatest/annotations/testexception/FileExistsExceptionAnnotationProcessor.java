package com.yediat.makeatest.annotations.testexception;

import java.io.File;

import com.yediat.makeatest.annotations.FailType;
import com.yediat.makeatest.core.MakeATestAssertionError;
import com.yediat.makeatest.core.metadata.processor.MetadataProcessor;

public class FileExistsExceptionAnnotationProcessor implements MetadataProcessor {

	private String filePath;
	private FailType failType;
	
	public FileExistsExceptionAnnotationProcessor(String filePath, FailType failType) {
		this.filePath = filePath;
		this.failType = failType;
	}

	@Override
	public void process(Object instance) throws MakeATestAssertionError {
		if(this.failType.equals(FailType.PROCESSOR)){
			new Integer("t");
		}
		File f = new File(filePath);
		if (!f.exists()) {
			throw new MakeATestAssertionError("Arquivo não existe.");
		}
	}
	

}
