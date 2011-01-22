package com.yediat.makeatest.core.container;

import com.yediat.makeatest.core.MakeATestAssertionError;
import com.yediat.makeatest.core.MakeATestEnum;
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
	private MakeATestEnum type;

	public MetadataProcessor getProcessor() {
		if (processor == null) {
			processor = new MetadataProcessor() {
				@Override
				public void process() throws MakeATestAssertionError { }
			};
		}
		return processor;
	}
	
	public void setProcessor(MetadataProcessor processor) {
		this.processor = processor;
	}

	public MakeATestEnum getType() {
		return type;
	}

	public void setType(MakeATestEnum type) {
		this.type = type;
	}

}
