package com.yediat.makeatest.annotations.fileverify;

import com.yediat.makeatest.core.container.AnnotationProperties;
import com.yediat.makeatest.core.metadata.reading.MakeATestReaderInterface;

public class FileNotExistsAnnotationReader implements MakeATestReaderInterface<FileNotExistsAnnotation> {

	@Override
	public void readAnnotation(FileNotExistsAnnotation annotation, AnnotationProperties annotationProperties) {
		String path = annotation.filePath();				
		FileNotExistsAnnotationProcessor processor = new FileNotExistsAnnotationProcessor(path);
		annotationProperties.setProcessor(processor);
	}

}
