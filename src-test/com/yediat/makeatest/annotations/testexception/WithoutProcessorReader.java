package com.yediat.makeatest.annotations.testexception;

import com.yediat.makeatest.core.container.AnnotationProperties;
import com.yediat.makeatest.core.metadata.reading.MakeATestReaderInterface;

public class WithoutProcessorReader implements MakeATestReaderInterface<WithoutProcessor> {

	@Override
	public void readAnnotation(WithoutProcessor annotation, AnnotationProperties annotationProperties) {
	}

}
