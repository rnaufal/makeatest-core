package com.yediat.makeatest.core;

import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.cglib.proxy.MethodProxy;

import com.yediat.makeatest.core.container.AnnotationProperties;
import com.yediat.makeatest.core.container.MetadataReader;
import com.yediat.makeatest.core.metadata.processor.MetadataProcessor;
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
		this.execute();
	}	
	
	private void execute() throws MakeATestException {
		Map<Object,List<AnnotationProperties>> properties = this.metadataReader.getContainer().getProperties(MakeATestScopeEnum.LOAD);
		
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

	public Object intercept(Object instance, Method method, Object[] args, MethodProxy proxy) throws Throwable {
//		if(this.makeATestController == null){
//		this.makeATestController = new MakeATestController(this.klass);
//	}
//	
//	Object objectForInvoke = null;
//	
//	if(this.makeATestController.contains(method)){
//		this.makeATestController.process(method, MakeATestEnum.PROCESS_BEFORE);
//		this.makeATestController.process(method, MakeATestEnum.PROCESS_BOTH);//TODO Refatorar para tentar usar o AFTER e BEFORE apenas(remover BOTH) 
//		try {
//			objectForInvoke =  method.invoke(this.object, args);
//		} catch (Exception e) {
//			throw e.getCause();
//		}
//		this.makeATestController.process(method, MakeATestEnum.PROCESS_BOTH);
//		this.makeATestController.process(method, MakeATestEnum.PROCESS_AFTER);
//		return objectForInvoke;			
//	} else {
//		objectForInvoke =  method.invoke(this.object, args);
//	}
//	
//	return objectForInvoke;
		return method.invoke(instance, args);
	}
	

	public void process(Method method, MakeATestEnum makeATestEnum) throws Throwable {
//		List<AnnotationProperties> props = this.metadataReader.getContainer().getProperties(method);
//
//		if(props != null) {
//			for (AnnotationProperties propertyDescriptor : props) {
//				MetadataProcessor metadataProcessor = propertyDescriptor.getProcessor(); 
//				if(propertyDescriptor.getType().equals(makeATestEnum)){
//					try {
//						metadataProcessor.process(this.instance);
//					} catch (Exception e) {
//						throw new MakeATestException("Exception in execute processor",e);
//					}
//					
//				}
//			}
//		}		
	}

}
