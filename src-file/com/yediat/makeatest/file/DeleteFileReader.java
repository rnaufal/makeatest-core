package com.yediat.makeatest.file;

import com.yediat.makeatest.core.container.PropertyDescriptor;
import com.yediat.makeatest.core.metadata.reading.MakeATestReaderInterface;

public class DeleteFileReader implements MakeATestReaderInterface<DeleteFile> {

	@Override
	public void readAnnotation(DeleteFile annotation, PropertyDescriptor descriptor) {
		String fileName = annotation.name();
		DeleteFileProcessor processor = new DeleteFileProcessor(fileName);
		descriptor.setProcessor(processor);
	}

}
