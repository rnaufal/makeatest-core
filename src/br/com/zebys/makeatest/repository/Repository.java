package br.com.zebys.makeatest.repository;

import java.lang.reflect.Method;

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
	private MetadataContainer metadataContainer;

	public static Repository getInstance() {
		if (instance == null) {
			instance = new Repository();
		}
		return instance;
	}

	private Repository() {
		reader = new MetadataReader();
		metadataContainer = null;
	}

	public MetadataContainer getMetadata(Method method) {
		if (metadataContainer == null) {
			metadataContainer = reader.createContainer(method);
		}
		return metadataContainer;
	}

}
