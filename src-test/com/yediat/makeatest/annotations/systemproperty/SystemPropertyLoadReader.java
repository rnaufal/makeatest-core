package com.yediat.makeatest.annotations.systemproperty;

import com.yediat.makeatest.core.container.AnnotationProperties;
import com.yediat.makeatest.core.metadata.reading.MakeATestReaderInterface;

public class SystemPropertyLoadReader implements MakeATestReaderInterface<SystemPropertyLoad> {

	@Override
	public void readAnnotation(SystemPropertyLoad annotation, AnnotationProperties descriptor) {
		SystemPropertyLoadProcessor systemPropertyLoadProcessor = new SystemPropertyLoadProcessor(annotation.key(), annotation.value());
		descriptor.setProcessor(systemPropertyLoadProcessor);
	}

}
