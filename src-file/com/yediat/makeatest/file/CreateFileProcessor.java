package com.yediat.makeatest.file;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.yediat.makeatest.core.MakeATestAssertionError;
import com.yediat.makeatest.core.metadata.processor.MetadataProcessor;

public class CreateFileProcessor implements MetadataProcessor {

	private String filePath;
	private String content;

	public CreateFileProcessor(String filePath, String content) {
		this.filePath = filePath;
		this.content = content;
	}

	@Override
	public void process() throws MakeATestAssertionError {
		File f = new File(filePath);
		BufferedWriter bw = null;
		try {
			f.createNewFile();
			bw = new BufferedWriter(new FileWriter(f));
			bw.write(content);
		} catch (Exception e) {
			throw new MakeATestAssertionError(e);
		} finally {
			if (bw != null) {
				try {
					bw.flush();
					bw.close();
				} catch (IOException e) {					
				}				
			}
		}
	}

}
