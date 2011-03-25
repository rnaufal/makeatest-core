package com.yediat.makeatest.annotations.testexception;

import com.yediat.makeatest.annotations.FailType;
import com.yediat.makeatest.core.container.AnnotationProperties;
import com.yediat.makeatest.core.metadata.reading.MakeATestReaderInterface;

public class FileExistsExceptionAnnotationReader implements MakeATestReaderInterface<FileExistsExceptionAnnotation> {

	@Override
	public void readAnnotation(FileExistsExceptionAnnotation annotation, AnnotationProperties annotationProperties) {
		if(annotation.failType().equals(FailType.READER)){
			new Integer("a");
		}
		String path = annotation.filePath();				
		FileExistsExceptionAnnotationProcessor processor = new FileExistsExceptionAnnotationProcessor(path,annotation.failType());
		annotationProperties.setProcessor(processor);
	}

}
