package com.yediat.makeatest.core.container;

import com.yediat.makeatest.core.metadata.processor.MetadataProcessor;

/**
 * Utilizado para armazenas a anotação que foi recuperada com o método a ser
 * executado e a classe executora referente a esse método.
 * 
 * @author deborachama
 * 
 */
public class PropertyDescriptor {

	private MetadataProcessor processor;

	public MetadataProcessor getProcessor() {
		if (processor == null) {
			processor = new MetadataProcessor() {
				@Override
				public void both() throws Exception {}
				@Override
				public void before() throws Exception {}
				@Override
				public void after() throws Exception {}
			};
		}
		return processor;
	}
	
	public void setProcessor(MetadataProcessor processor) {
		this.processor = processor;
	}

}
