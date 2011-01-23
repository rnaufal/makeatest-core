package com.yediat.makeatest.file;

import java.io.File;

import com.yediat.makeatest.core.MakeATestAssertionError;
import com.yediat.makeatest.core.metadata.processor.MetadataProcessor;

public class DeleteFileProcessor implements MetadataProcessor {

	private String filePath;

	public DeleteFileProcessor(String filePath) {
		this.filePath = filePath;
	}

	@Override
	public void process() throws MakeATestAssertionError {
		File f = new File(filePath);
		try {
			if(f.exists()){
				f.delete();
			}
		} catch (Exception e) {
			throw new MakeATestAssertionError(e);
		}
	}

}
