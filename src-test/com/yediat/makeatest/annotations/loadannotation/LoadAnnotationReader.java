package com.yediat.makeatest.annotations.loadannotation;

import java.lang.reflect.Field;

import com.yediat.makeatest.annotations.FailType;
import com.yediat.makeatest.core.container.AnnotationProperties;
import com.yediat.makeatest.core.metadata.reading.MakeATestReaderInterface;

public class LoadAnnotationReader implements MakeATestReaderInterface<LoadAnnotation> {

	@Override
	public void readAnnotation(LoadAnnotation annotation, AnnotationProperties descriptor) {
		if(annotation.failType().equals(FailType.READER)){
			new Integer("a");
		}
		if(annotation.withProcessor()) {
			LoadAnnotationProcessor objectLoadProcessor = new LoadAnnotationProcessor((Field) descriptor.getAnnotated(), annotation.value(),annotation.failType());
			descriptor.setProcessor(objectLoadProcessor);			
		}
	}

}
