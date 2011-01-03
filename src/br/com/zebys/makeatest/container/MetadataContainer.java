package br.com.zebys.makeatest.container;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Responsável por armazenar as informações dos metadados das classes.
 * @author deborachama
 *
 * @see PropertyDescriptor
 *
 */
public class MetadataContainer {
	private Map<Method, List<PropertyDescriptor>> properties;

	/**
	 * Construturo para iniciar o {@link PropertyDescriptor}, sendo o mesmo uma uníca instancia.
	 */
	public MetadataContainer() {
		this.properties = new HashMap<Method, List<PropertyDescriptor>>();
	}

	/**
	 * Adiciona no container o método como chave e o {@link PropertyDescriptor} nesta chave com as informações das anotações. 
	 * @param method
	 * @param propertyDescriptor
	 */
	public void put(Method method, PropertyDescriptor propertyDescriptor) {
		if (!this.properties.containsKey(method)) {
			List<PropertyDescriptor> props = new ArrayList<PropertyDescriptor>();
			props.add(propertyDescriptor);
			this.properties.put(method, props);
		} else {
			this.properties.get(method).add(propertyDescriptor);
		}
	}

	/**
	 * Recupera o MAP com os {@link PropertyDescriptor}
	 * @return Mapa com as chaves métodos e cada chave representa um {@link PropertyDescriptor}
	 */
	public Map<Method, List<PropertyDescriptor>> getProperties() {
		return this.properties;
	}
	
	/**
	 * Retorna uma lista de {@link PropertyDescriptor} a patir do do método
	 * @param method Método que representa a anotação
	 * @return {@link PropertyDescriptor}
	 */
	public List<PropertyDescriptor> getProperties(Method method) {
		return this.properties.get(method);
	}
}
