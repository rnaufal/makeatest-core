package com.yediat.makeatest.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.cglib.proxy.MethodProxy;

import com.yediat.makeatest.core.container.AnnotationProperties;
import com.yediat.makeatest.core.container.MetadataReader;
import com.yediat.makeatest.core.metadata.processor.MetadataProcessor;
import com.yediat.makeatest.core.metadata.reading.MakeATestProxyBehavior;
import com.yediat.makeatest.core.metadata.reading.MakeATestReader;
import com.yediat.makeatest.core.metadata.reading.MakeATestScope;

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
		Map<Object,List<AnnotationProperties>> properties = this.metadataReader.getContainer().getProperties(MakeATestScope.LOAD);
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

	private Object execute(Object object, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		boolean isExecuted = false;
		Object invoked = null;
		Map<Object,List<AnnotationProperties>> properties = this.metadataReader.getContainer().getProperties(MakeATestScope.PROXYMETHOD);
		if(properties != null && properties.containsKey(method)){
			List<AnnotationProperties> props = properties.get(method);
			if(props != null) {
				for (AnnotationProperties annotationProperties : props) {
					MetadataProcessor metadataProcessor = annotationProperties.getProcessor(); 

					HashSet<MakeATestProxyBehavior> enums = new HashSet<MakeATestProxyBehavior>();
					for(MakeATestProxyBehavior makeATestActionEnum : annotationProperties.getActions()){
						enums.add(makeATestActionEnum);
					}
					
					if(enums.contains(MakeATestProxyBehavior.BEFORE) && enums.contains(MakeATestProxyBehavior.AFTER)) {
						try {
							metadataProcessor.process(this.instance);
							invoked = method.invoke(object, args);
							metadataProcessor.process(this.instance);
							isExecuted = true;
						} catch (InvocationTargetException e) {
							throw e.getCause();
						} catch (Exception e) {
							throw new MakeATestException("Exception in execute processor",e);
						}						
					} else if(enums.contains(MakeATestProxyBehavior.BEFORE)) {
						try {
							metadataProcessor.process(this.instance);
							invoked = method.invoke(object, args);
							isExecuted = true;
						} catch (InvocationTargetException e) {
							throw e.getCause();
						} catch (Exception e) {
							throw new MakeATestException("Exception in execute processor",e);
						}
					} else if(enums.contains(MakeATestProxyBehavior.AFTER)) {
						try {
							invoked = method.invoke(object, args);
							metadataProcessor.process(this.instance);
							isExecuted = true;
						} catch (InvocationTargetException e) {
							throw e.getCause();
						} catch (Exception e) {
							MakeATestException makeATestException = new MakeATestException("Exception in processor class: " + e);
							makeATestException.setStackTrace(e.getStackTrace());
							throw makeATestException;
						}						
					}
				}
			}
			if(!isExecuted){
				throw new MakeATestException("Verify the implementation of annotation, reader and processor.");
			}
		}
		if(!isExecuted){
			try {
				invoked = method.invoke(object, args);
			} catch (InvocationTargetException e) {
				throw e.getCause();
			}			
		}
		return invoked;
	}
	
	/**
	 * Método que é chamado pelo intercept do Proxy de cada método executado da instancia com proxy.
	 * Esse método verifica se o método executado contêm uma anotação com MakeATestReader e delega o processamento para o método execute dessa classe.
	 * Caso não seja apenas executo o invoke e retorna o objeto invocado.
	 * 
	 * @param instance
	 * @param method
	 * @param args
	 * @param proxy
	 * @return
	 * @throws Throwable
	 */
	public Object intercept(Object instance, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		boolean methodExecute = false;
		Annotation [] annotations = method.getAnnotations();
		for (Annotation annotation : annotations) {
			if(annotation.annotationType().isAnnotationPresent(MakeATestReader.class)){
				methodExecute = true;
			}
		}
		if(methodExecute){
			return this.execute(instance, method, args, proxy);
		} else {
			try {
				return method.invoke(instance, args);
			} catch (InvocationTargetException e) {
				throw e.getCause();
			}
		}
	}
	
}
