package com.yediat.makeatest.annotations.objectload;

import java.lang.reflect.Field;

import com.yediat.makeatest.core.container.AnnotationProperties;
import com.yediat.makeatest.core.metadata.reading.MakeATestReaderInterface;

public class ObjectLoadReader implements MakeATestReaderInterface<ObjectLoad> {

	@Override
	public void readAnnotation(ObjectLoad annotation, AnnotationProperties descriptor) {
		String value = annotation.value();
		ObjectLoadProcessor objectLoadProcessor = new ObjectLoadProcessor((Field) descriptor.getAnnotated(), value);
		descriptor.setProcessor(objectLoadProcessor);
	}

}
