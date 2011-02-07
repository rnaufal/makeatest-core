package com.yediat.makeatest.core;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.cglib.proxy.MethodProxy;

import com.yediat.makeatest.core.container.AnnotationProperties;
import com.yediat.makeatest.core.container.MetadataReader;
import com.yediat.makeatest.core.metadata.processor.MetadataProcessor;
import com.yediat.makeatest.core.metadata.reading.MakeATestExecutionEnum;
import com.yediat.makeatest.core.metadata.reading.MakeATestScopeEnum;

/**
 * Class princiapl de entrada no core.
 * @author marcusfloriano
 *
 */
public class MakeATestController {

	private Object instance;
	private MetadataReader metadataReader;
	
	public MakeATestController(Object instance) throws MakeATestInitializationException, MakeATestException {
		this.instance = instance;
		this.metadataReader = new MetadataReader(this.instance.getClass());
		this.load();
	}	

	public Object getObjectInstance() {
		return this.instance;
	}
	
	public Object getObjectInstanceProxy() throws MakeATestInitializationException {
		try {
			return MakeATestProxy.getProxy(this.instance,this);
		} catch (Exception e) {
			throw new MakeATestInitializationException("Error creating proxy",e);
		}
	}

	private void load() throws MakeATestException {
		Map<Object,List<AnnotationProperties>> properties = this.metadataReader.getContainer().getProperties(MakeATestScopeEnum.LOAD);
		if(properties != null){
			Iterator<Object> iterator = properties.keySet().iterator();
			while(iterator.hasNext()){
				Object object = iterator.next();
				List<AnnotationProperties> props = properties.get(object);
				if(props != null) {
					for (AnnotationProperties propertyDescriptor : props) {
						MetadataProcessor metadataProcessor = propertyDescriptor.getProcessor(); 
						try {
							metadataProcessor.process(this.instance);
						} catch (Exception e) {
							throw new MakeATestException("Exception in execute processor",e);
						}
					}
				}		
			}			
		}
	}

	private Object execute(Object instance, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		Object invoked = null;
		Map<Object,List<AnnotationProperties>> properties = this.metadataReader.getContainer().getProperties(MakeATestScopeEnum.EXECUTE);
		if(properties != null && properties.containsKey(method)){
			List<AnnotationProperties> props = properties.get(method);
			if(props != null) {
				for (AnnotationProperties annotationProperties : props) {
					MetadataProcessor metadataProcessor = annotationProperties.getProcessor(); 
					
					if(annotationProperties.getExecution().equals(MakeATestExecutionEnum.BEFORE)) {
						try {
							metadataProcessor.process(this.instance);
							invoked = method.invoke(instance, args);
						} catch (Exception e) {
							throw new MakeATestException("Exception in execute processor",e);
						}
					} else if(annotationProperties.getExecution().equals(MakeATestExecutionEnum.AFTER)) {
						try {
							invoked = method.invoke(instance, args);
							metadataProcessor.process(this.instance);
						} catch (Exception e) {
							throw new MakeATestException("Exception in execute processor",e);
						}						
					}
					
					
				}
			}			
		}
		return invoked;
	}
	
	public Object intercept(Object instance, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		return this.execute(instance, method, args, proxy);
	}
	
}
