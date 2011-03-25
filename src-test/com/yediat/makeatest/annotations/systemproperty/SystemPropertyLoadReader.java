package com.yediat.makeatest.annotations.systemproperty;

import com.yediat.makeatest.core.container.AnnotationProperties;
import com.yediat.makeatest.core.metadata.reading.MakeATestReaderInterface;

public class SystemPropertyLoadReader implements MakeATestReaderInterface<SystemPropertyLoad> {

	@Override
	public void readAnnotation(SystemPropertyLoad annotation, AnnotationProperties annotationProperties) {
		SystemPropertyLoadProcessor systemPropertyLoadProcessor = new SystemPropertyLoadProcessor(annotation.key(), annotation.value());
		annotationProperties.setProcessor(systemPropertyLoadProcessor);
	}

}
