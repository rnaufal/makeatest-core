package br.com.zebys.makeatest.repository;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import br.com.zebys.makeatest.container.MetadataContainer;
import br.com.zebys.makeatest.container.MetadataReader;

/**
 * Repository
 * @author deborachama
 *
 */
public class Repository {
	
	//singleton
	private static Repository instance;
	
	private MetadataReader reader;
	private Map<Method, MetadataContainer> cache;

	public static Repository getInstance() {
		if (instance == null) {
			instance = new Repository();
		}
		return instance;
	}

	private Repository() {
		reader = new MetadataReader();
		cache = new HashMap<Method, MetadataContainer>();
	}

	public MetadataContainer getMetadata(Method method) {
		if (cache.containsKey(method)) {
			return cache.get(method);
		}
		MetadataContainer metadataContainer = reader.createContainer(method);
		cache.put(method, metadataContainer);
		return metadataContainer;
	}

}
