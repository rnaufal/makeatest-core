package com.yediat.makeatest.core.metadata.processor;

import com.yediat.makeatest.core.MakeATestAssertionError;
import com.yediat.makeatest.core.MakeATestEnum;

/**
 * Interface a ser utilizada na implementação do "processor" da anotação. 
 * 
 * @author marcusfloriano
 *
 */
public abstract class MetadataProcessor {
	private MakeATestEnum type = MakeATestEnum.PROCESS_AFTER;
	
	public MakeATestEnum getType() {
		return type;
	}
	public void setType(MakeATestEnum type) {
		this.type = type;
	}
	
	public abstract void process() throws MakeATestAssertionError;
}
