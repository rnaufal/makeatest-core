package com.yediat.makeatest.fileverify;

import com.yediat.makeatest.core.MakeATestEnum;
import com.yediat.makeatest.core.container.PropertyDescriptor;
import com.yediat.makeatest.core.metadata.reading.MakeATestReaderInterface;

public class FileNotExistsAnnotationReader implements MakeATestReaderInterface<FileNotExistsAnnotation> {

	@Override
	public void readAnnotation(FileNotExistsAnnotation annotation, PropertyDescriptor descriptor) {
		String path = annotation.filePath();				
		FileNotExistsAnnotationProcessor processor = new FileNotExistsAnnotationProcessor(path);
		processor.setType(MakeATestEnum.PROCESS_AFTER);
		descriptor.setProcessor(processor);
	}

}
