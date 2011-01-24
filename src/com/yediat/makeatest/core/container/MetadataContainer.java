package com.yediat.makeatest.core.container;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Utilizado para armazenar objetos do tipo {@link PropertyDescriptor}. 
 * @author deborachama
 *
 */
public class MetadataContainer {
	/*
	 * A chave é o metodo de teste. Pode ter mais de uma anotacao por metodo, entao ele tem varios PropertyDescriptor
	 */
	private Map<Method, List<PropertyDescriptor>> properties;

	public MetadataContainer() {
		this.properties = new HashMap<Method, List<PropertyDescriptor>>();
	}
	
	public boolean contains(Method method) {
		return properties.containsKey(method);
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
