package com.yediat.makeatest.core.metadata.processor;

import java.util.List;

import com.yediat.makeatest.core.MakeATestAssertionError;

public class CompositeMetadataProcessor implements MetadataProcessor {
	
	private List<MetadataProcessor> processors;

	@Override
	public void process(Object instance) throws MakeATestAssertionError {
		for(MetadataProcessor mp : processors){
			System.out.println("mp.process: " + mp);
			mp.process(instance);
		}
	}

}
