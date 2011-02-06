package com.yediat.makeatest.core.container;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utilizado para armazenar objetos do tipo {@link AnnotationProperties}. 
 * @author deborachama
 *
 */
public class MetadataContainer {
	private Map<Object, List<AnnotationProperties>> properties;

	public MetadataContainer() {
		this.properties = new HashMap<Object, List<AnnotationProperties>>();
	}
	
	public boolean contains(Method method) {
		return properties.containsKey(method);
	}

	public void put(Object object, AnnotationProperties propertyDescriptor) {
		if (!this.properties.containsKey(object)) {
			List<AnnotationProperties> props = new ArrayList<AnnotationProperties>();
			props.add(propertyDescriptor);
			this.properties.put(object, props);
		} else {
			this.properties.get(object).add(propertyDescriptor);
		}
	}

	public Map<Object, List<AnnotationProperties>> getProperties() {
		return this.properties;
	}
	
	public List<AnnotationProperties> getProperties(Object object) {
		return this.properties.get(object);
	}
}
