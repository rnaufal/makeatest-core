package com.yediat.makeatest.annotations.proxymethod.annotation;

import com.yediat.makeatest.annotations.FailType;
import com.yediat.makeatest.core.container.AnnotationProperties;
import com.yediat.makeatest.core.metadata.reading.MakeATestReaderInterface;

public class ProxyMethodBeforeAfterAnnotationReader implements MakeATestReaderInterface<ProxyMethodBeforeAfterAnnotation> {

	@Override
	public void readAnnotation(ProxyMethodBeforeAfterAnnotation annotation, AnnotationProperties annotationProperties) {
		ProxyMethodBeforeAfterAnnotation annot = (ProxyMethodBeforeAfterAnnotation) annotation;
		if(annot.failType().equals(FailType.READER)){
			new Integer("a");
		}
		ProxyMethodBeforeAfterAnnotationProcessor proxyMethodBeforeAfterAnnotationProcessor = new ProxyMethodBeforeAfterAnnotationProcessor(annot.variable(),annot.textBefore(),annot.textAfter(),annot.failType());
		annotationProperties.setProcessor(proxyMethodBeforeAfterAnnotationProcessor);			
	}

}
