package com.yediat.makeatest.annotations.fileverify;

import com.yediat.makeatest.core.container.AnnotationProperties;
import com.yediat.makeatest.core.metadata.reading.MakeATestReaderInterface;

public class FileExistsAnnotationReader implements MakeATestReaderInterface<FileExistsAnnotation> {

	@Override
	public void readAnnotation(FileExistsAnnotation annotation, AnnotationProperties annotationProperties) {
		String path = annotation.filePath();				
		FileExistsAnnotationProcessor processor = new FileExistsAnnotationProcessor(path);
		annotationProperties.setProcessor(processor);
	}

}
