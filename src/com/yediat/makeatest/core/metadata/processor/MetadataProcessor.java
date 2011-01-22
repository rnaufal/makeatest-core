package com.yediat.makeatest.core.metadata.processor;

import com.yediat.makeatest.core.MakeATestAssertionError;

/**
 * Interface a ser utilizada na implementação do "processor" da anotação. 
 * 
 * @author marcusfloriano
 *
 */
public interface MetadataProcessor {
	public abstract void process() throws MakeATestAssertionError;
}
