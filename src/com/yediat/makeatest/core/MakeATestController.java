package com.yediat.makeatest.core;

import java.lang.reflect.Method;
import java.util.List;

import com.yediat.makeatest.core.container.MetadataReader;
import com.yediat.makeatest.core.container.PropertyDescriptor;
import com.yediat.makeatest.core.metadata.processor.MetadataProcessor;

/**
 * Class princiapl de entrada no core.
 * @author marcusfloriano
 *
 */
public class MakeATestController {

	private MetadataReader metadataReader;

	public MakeATestController(Class<?> klass) throws MakeATestInitializationException {
		this.metadataReader = new MetadataReader(klass);
	}
		
	public boolean contains(Method method) throws Throwable {
		return this.metadataReader.contains(method);
	}

	public void process(Method method, MakeATestEnum makeATestEnum) throws Throwable {
		List<PropertyDescriptor> props = this.metadataReader.getContainer().getProperties(method);

		if(props != null) {
			for (PropertyDescriptor propertyDescriptor : props) {
				MetadataProcessor metadataProcessor = propertyDescriptor.getProcessor(); 
				if(propertyDescriptor.getType().equals(makeATestEnum)){
					try {
						metadataProcessor.process();
					} catch (Exception e) {
						throw new MakeATestException("Exception in execute processor",e);
					}
					
				}
			}
		}		
	}

}
