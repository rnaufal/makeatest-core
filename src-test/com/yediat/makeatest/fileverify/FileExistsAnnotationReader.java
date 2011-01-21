package com.yediat.makeatest.fileverify;

import com.yediat.makeatest.core.MakeATestEnum;
import com.yediat.makeatest.core.container.PropertyDescriptor;
import com.yediat.makeatest.core.metadata.reading.MakeATestReaderInterface;

public class FileExistsAnnotationReader implements MakeATestReaderInterface<FileExistsAnnotation> {

	@Override
	public void readAnnotation(FileExistsAnnotation annotation, PropertyDescriptor descriptor) {
		String path = annotation.filePath();				
		FileExistsAnnotationProcessor processor = new FileExistsAnnotationProcessor(path);
		processor.setType(MakeATestEnum.PROCESS_AFTER);
		descriptor.setProcessor(processor);
	}

}
