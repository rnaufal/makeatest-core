package com.yediat.makeatest.file;

import com.yediat.makeatest.core.container.PropertyDescriptor;
import com.yediat.makeatest.core.metadata.reading.MakeATestReaderInterface;

public class FileNotContainsReader implements MakeATestReaderInterface<FileNotContains> {

	@Override
	public void readAnnotation(FileNotContains annotation, PropertyDescriptor descriptor) {
		String fileName = annotation.name();
		String contentFile = annotation.content();
		VerifyFileContentProcessor processor = new VerifyFileContentProcessor(fileName, contentFile, false);
		descriptor.setProcessor(processor);
	}

}
