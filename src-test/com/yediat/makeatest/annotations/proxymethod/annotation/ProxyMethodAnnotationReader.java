package com.yediat.makeatest.annotations.proxymethod.annotation;

import java.lang.annotation.Annotation;

import com.yediat.makeatest.annotations.FailType;
import com.yediat.makeatest.core.container.AnnotationProperties;
import com.yediat.makeatest.core.metadata.reading.MakeATestReaderInterface;

public class ProxyMethodAnnotationReader implements MakeATestReaderInterface<Annotation> {

	@Override
	public void readAnnotation(Annotation annotation, AnnotationProperties descriptor) {
		if(annotation instanceof ProxyMethodBeforeAnnotation){
			ProxyMethodBeforeAnnotation annot = (ProxyMethodBeforeAnnotation) annotation;
			if(annot.failType().equals(FailType.READER)){
				new Integer("a");
			}
			ProxyMethodAnnotationProcessor proxyAnnotationProcessor = new ProxyMethodAnnotationProcessor(annot.variable(),annot.text(),annot.failType());
			descriptor.setProcessor(proxyAnnotationProcessor);			
		} else if(annotation instanceof ProxyMethodAfterAnnotation) {
			ProxyMethodAfterAnnotation annot = (ProxyMethodAfterAnnotation) annotation;
			if(annot.failType().equals(FailType.READER)){
				new Integer("a");
			}
			ProxyMethodAnnotationProcessor proxyAnnotationProcessor = new ProxyMethodAnnotationProcessor(annot.variable(),annot.text(),annot.failType());
			descriptor.setProcessor(proxyAnnotationProcessor);			
		} else  if(annotation instanceof ProxyMethodClassCastExceptionInReaderAnnotation) {
			ProxyMethodAnnotationProcessor proxyAnnotationProcessor = new ProxyMethodAnnotationProcessor(null,null,null);
			descriptor.setProcessor(proxyAnnotationProcessor);
		}
	}

}
