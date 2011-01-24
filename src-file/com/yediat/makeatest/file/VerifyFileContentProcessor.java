package com.yediat.makeatest.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import com.yediat.makeatest.core.MakeATestAssertionError;
import com.yediat.makeatest.core.metadata.processor.MetadataProcessor;

public class VerifyFileContentProcessor implements MetadataProcessor {

	private String filePath;
	private String content;
	private boolean shouldContains;

	public VerifyFileContentProcessor(String filePath, String content, boolean shouldContains) {
		this.filePath = filePath;
		this.content = content;
		this.shouldContains = shouldContains;
	}

	@Override
	public void process() throws MakeATestAssertionError {
		File file = new File(filePath);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(file));
			StringBuffer fileContent = new StringBuffer();
			String line = null;
			int lineNumber = 0;
			while ((line = br.readLine()) != null) {
				if (lineNumber > 0)
					fileContent.append("\n");
				fileContent.append(line);
				lineNumber++;
			}

			boolean fileContainsContent = fileContent.toString().contains(content);
			if ((fileContainsContent && !shouldContains) || (!fileContainsContent && shouldContains)) {
				throw new MakeATestAssertionError("Conteudo do arquivo não verificado.");
			}

		} catch (Exception e) {
			throw new MakeATestAssertionError(e);
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					throw new MakeATestAssertionError(e);
				}
			}
		}
	}

}
