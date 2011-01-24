package com.yediat.makeatest.file;

import com.yediat.makeatest.core.container.PropertyDescriptor;
import com.yediat.makeatest.core.metadata.reading.MakeATestReaderInterface;

public class FileContainsReader implements MakeATestReaderInterface<FileContains> {

	@Override
	public void readAnnotation(FileContains annotation, PropertyDescriptor descriptor) {
		String fileName = annotation.name();
		String contentFile = annotation.content();
		VerifyFileContentProcessor processor = new VerifyFileContentProcessor(fileName, contentFile, true);
		descriptor.setProcessor(processor);
	}

}
