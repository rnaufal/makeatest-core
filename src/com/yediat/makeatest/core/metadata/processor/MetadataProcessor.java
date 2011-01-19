package com.yediat.makeatest.core.metadata.processor;

/**
 * Interface a ser utilizada na implementação do "processor" da anotação. 
 * 
 * @author marcusfloriano
 *
 */
public interface MetadataProcessor {
	public void both() throws Exception;
	public void before() throws Exception;
	public void after() throws Exception;
}
