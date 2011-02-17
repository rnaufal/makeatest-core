package com.yediat.makeatest.core.metadata.processor;

import com.yediat.makeatest.core.MakeATestAssertionError;
import com.yediat.makeatest.core.metadata.reading.MakeATestProxyBehavior;

/**
 * Interface a ser utilizada na implementação do "processor" da anotação. 
 * 
 * @author marcusfloriano
 *
 */
public abstract class MetadataProcessor {
	
	protected MakeATestProxyBehavior makeATestProxyBehavior = MakeATestProxyBehavior.NONE;
	
	public void setProxyBehavior(MakeATestProxyBehavior makeATestProxyBehavior){
		this.makeATestProxyBehavior = makeATestProxyBehavior;
	}
	
	public abstract void process(Object instance) throws MakeATestAssertionError;
}
