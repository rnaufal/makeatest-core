package com.yediat.makeatest.annotations.testexception;

import com.yediat.makeatest.core.container.AnnotationProperties;
import com.yediat.makeatest.core.metadata.reading.MakeATestReaderInterface;

public class FileExistsExceptionAnnotationReader implements MakeATestReaderInterface<FileExistsExceptionAnnotation> {

	@Override
	public void readAnnotation(FileExistsExceptionAnnotation annotation, AnnotationProperties descriptor) {
		if(annotation.failType().equals("reader")){
			new Integer("a");
		}
		String path = annotation.filePath();				
		FileExistsExceptionAnnotationProcessor processor = new FileExistsExceptionAnnotationProcessor(path,annotation.failType());
		descriptor.setProcessor(processor);
	}

}
