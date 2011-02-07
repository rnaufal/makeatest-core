package com.yediat.makeatest.core.container;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yediat.makeatest.core.metadata.reading.MakeATestScopeEnum;

/**
 * Utilizado para armazenar objetos do tipo {@link AnnotationProperties}. 
 * @author deborachama
 *
 */
public class MetadataContainer {
	
	private Map<MakeATestScopeEnum, Map<Object, List<AnnotationProperties>>> container;
	
	public MetadataContainer() {
		this.container = new HashMap<MakeATestScopeEnum, Map<Object,List<AnnotationProperties>>>();
	}
	
	public void put(MakeATestScopeEnum makeATestScopeEnum, Object object, AnnotationProperties annotationProperties) {
		
		if(!this.container.containsKey(makeATestScopeEnum)) {
			List<AnnotationProperties> props = new ArrayList<AnnotationProperties>();
			props.add(annotationProperties);
			HashMap<Object, List<AnnotationProperties>> properties = new HashMap<Object, List<AnnotationProperties>>();
			properties.put(object, props);
			this.container.put(makeATestScopeEnum, properties);
		} else {
			this.container.get(makeATestScopeEnum).get(object).add(annotationProperties);
		}
	}
	
	public Map<Object, List<AnnotationProperties>> getProperties(MakeATestScopeEnum makeATestScopeEnum) {
		if(!this.container.containsKey(makeATestScopeEnum)){
			return null;
		}
		return this.container.get(makeATestScopeEnum);
	}
	
	public List<AnnotationProperties> getProperties(MakeATestScopeEnum makeATestScopeEnum, Object object) {
		return this.container.get(makeATestScopeEnum).get(object);
	}
}
