package com.yediat.makeatest.container;

import com.yediat.makeatest.metadataprocessor.DefaultMetadataProcessor;
import com.yediat.makeatest.metadataprocessor.MetadataProcessor;

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
			processor = new DefaultMetadataProcessor();
		}
		return processor;
	}
	
	public void setProcessor(MetadataProcessor processor) {
		this.processor = processor;
	}

}
