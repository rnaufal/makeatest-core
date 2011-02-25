package com.yediat.makeatest.core.container;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yediat.makeatest.core.metadata.reading.MakeATestScope;

/**
 * Utilizado para armazenar objetos do tipo {@link AnnotationProperties}.
 * 
 * @author deborachama
 * 
 */
public class MetadataContainer {

	final Logger logger = LoggerFactory.getLogger(MetadataContainer.class);
	
	private Map<MakeATestScope, Map<Object, List<AnnotationProperties>>> container;

	public MetadataContainer() {
		if(logger.isDebugEnabled()){logger.debug("Create container.");}
		this.container = new HashMap<MakeATestScope, Map<Object, List<AnnotationProperties>>>();
	}

	public void put(MakeATestScope makeATestScopeEnum, Object object, AnnotationProperties annotationProperties) {
		if (!this.container.containsKey(makeATestScopeEnum)) {
			if(logger.isDebugEnabled()){logger.debug("no contains key " + makeATestScopeEnum);}
			List<AnnotationProperties> props = new ArrayList<AnnotationProperties>();
			props.add(annotationProperties);
			HashMap<Object, List<AnnotationProperties>> properties = new HashMap<Object, List<AnnotationProperties>>();
			properties.put(object, props);
			this.container.put(makeATestScopeEnum, properties);
		} else if (!this.container.get(makeATestScopeEnum).containsKey(object)) {
			if(logger.isDebugEnabled()){logger.debug("no contains object " + object);}
			List<AnnotationProperties> props = new ArrayList<AnnotationProperties>();
			props.add(annotationProperties);
			this.container.get(makeATestScopeEnum).put(object, props);
		} else if (this.container.get(makeATestScopeEnum).containsKey(object)) {
			if(logger.isDebugEnabled()){logger.debug("put props in object existent");}
			List<AnnotationProperties> props = this.container.get(makeATestScopeEnum).get(object);
			props.add(annotationProperties);
		}
	}

	public Map<Object, List<AnnotationProperties>> getProperties(MakeATestScope makeATestScopeEnum) {
		if (!this.container.containsKey(makeATestScopeEnum)) {
			return null;
		}
		return this.container.get(makeATestScopeEnum);
	}
}
