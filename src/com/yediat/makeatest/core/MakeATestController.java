package com.yediat.makeatest.core;

import java.lang.reflect.Method;
import java.util.List;

import com.yediat.makeatest.core.container.PropertyDescriptor;
import com.yediat.makeatest.core.metadata.processor.MetadataProcessor;
import com.yediat.makeatest.core.repository.Repository;

/**
 * Class princiapl de entrada no core.
 * @author marcusfloriano
 *
 */
public class MakeATestController {

	private Repository repository;

	public MakeATestController() {
		this.repository = Repository.getInstance();
	}

	public void process(Method method, MakeATestEnum makeATestEnum) throws Throwable {
		this.repository.getMetadata(method);
		List<PropertyDescriptor> props = this.repository.getMetadata(method).getProperties(method);

		if(props != null) {
			for (PropertyDescriptor propertyDescriptor : props) {
				MetadataProcessor metadataProcessor = propertyDescriptor.getProcessor(); 
				if(propertyDescriptor.getType().equals(makeATestEnum)){
					metadataProcessor.process();
				}
			}
		}		
	}

}
