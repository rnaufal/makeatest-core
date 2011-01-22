package com.yediat.makeatest.file;

import com.yediat.makeatest.core.container.PropertyDescriptor;
import com.yediat.makeatest.core.metadata.reading.MakeATestReaderInterface;

public class CreateFileReader implements MakeATestReaderInterface<CreateFile> {

	@Override
	public void readAnnotation(CreateFile annotation, PropertyDescriptor descriptor) {
		String fileName = annotation.name();
		String contentFile = annotation.content();
		CreateFileProcessor processor = new CreateFileProcessor(fileName, contentFile);
		descriptor.setProcessor(processor);
	}

}
