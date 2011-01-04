package com.yediat.makeatest.container;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Container
 * @author deborachama
 *
 */
public class MetadataContainer {
	private Map<Method, List<PropertyDescriptor>> properties;

	public MetadataContainer() {
		this.properties = new HashMap<Method, List<PropertyDescriptor>>();
	}

	public void put(Method method, PropertyDescriptor propertyDescriptor) {
		if (!this.properties.containsKey(method)) {
			List<PropertyDescriptor> props = new ArrayList<PropertyDescriptor>();
			props.add(propertyDescriptor);
			this.properties.put(method, props);
		} else {
			this.properties.get(method).add(propertyDescriptor);
		}
	}

	public Map<Method, List<PropertyDescriptor>> getProperties() {
		return this.properties;
	}
	
	public List<PropertyDescriptor> getProperties(Method method) {
		return this.properties.get(method);
	}
}
