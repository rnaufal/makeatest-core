package com.yediat.makeatest.fileverify;

import com.yediat.makeatest.core.container.AnnotationProperties;
import com.yediat.makeatest.core.metadata.reading.MakeATestReaderInterface;

public class FileExistsAnnotationReader implements MakeATestReaderInterface<FileExistsAnnotation> {

	@Override
	public void readAnnotation(FileExistsAnnotation annotation, AnnotationProperties descriptor) {
		String path = annotation.filePath();				
		FileExistsAnnotationProcessor processor = new FileExistsAnnotationProcessor(path);
		descriptor.setProcessor(processor);
	}

}
