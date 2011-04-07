package com.yediat.makeatest.core;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import junit.framework.AssertionFailedError;

import net.sf.cglib.proxy.MethodProxy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yediat.makeatest.core.container.AnnotationProperties;
import com.yediat.makeatest.core.container.MetadataReader;
import com.yediat.makeatest.core.metadata.processor.AnnotationProcessor;
import com.yediat.makeatest.core.metadata.reading.MakeATestProxyBehavior;
import com.yediat.makeatest.core.metadata.reading.MakeATestReader;
import com.yediat.makeatest.core.metadata.reading.MakeATestScope;

/**
 * Class princiapl de entrada no core.
 * @author marcusfloriano
 *
 */
public class MakeATestController {

	final Logger logger = LoggerFactory.getLogger(MakeATestController.class);
			
	private Object instance;
	private MetadataReader metadataReader;
	
	/**
	 * O controller do make a test é a classe inicial para execução do framework.
	 * Para a utilização do framework é necessário passar uma instancia da classe que contêm as anotações criadas para o framework.
	 * 
	 * MakeATestController controller = new MakeATestController(new ClassWithAnnotaionMakeATest());
	 * ClassWithAnnotaionMakeATest instanceOfClass = (ClassWithAnnotaionMakeATest) controller.getInstance();
	 * 
	 * @param instance - Instancia da classe que contêm as anotações que deveram ser processadas.
	 * @throws MakeATestInitializationException
	 * @throws MakeATestException
	 */
	public MakeATestController(Object instance) throws MakeATestInitializationException, MakeATestException {
		if(logger.isDebugEnabled()){logger.debug("Parameter instance is " + instance.toString());}
		this.instance = instance;
		this.metadataReader = new MetadataReader(this.instance.getClass());
		this.load();
	}
	
	/**
	 * Retorna a instancia do objeto processado com os devidos loads carregados.
	 * @return
	 */
	public Object getObjectInstance() {
		return this.instance;
	}
	
	/**
	 * Retorna a instancia do objeto mas quando algum método é chamado o proxy intercepta para executar as anotações de método
	 * @return
	 * @throws MakeATestInitializationException
	 */
	public Object getObjectInstanceProxy() throws MakeATestInitializationException {
		try {
			return MakeATestProxy.getProxy(this.instance,this);
		} catch (Exception e) {
			throw new MakeATestInitializationException("Error creating proxy",e);
		}
	}

	/**
	 * Faz o processamento do objeto para as anotações do tipo LOAD
	 * @throws MakeATestException
	 */
	private void load() throws MakeATestException {
		if(logger.isDebugEnabled()){logger.debug("Load process");}
		Map<Object,List<AnnotationProperties>> properties = this.metadataReader.getContainer().getProperties(MakeATestScope.LOAD);
		if(properties != null){
			Iterator<Object> iterator = properties.keySet().iterator();
			while(iterator.hasNext()){
				Object object = iterator.next();
				List<AnnotationProperties> props = properties.get(object);
				if(props != null) {
					for (AnnotationProperties annotationProperties : props) {
						AnnotationProcessor annotationProcessor = annotationProperties.getProcessor(); 
						try {
							annotationProcessor.process(this.instance);
						} catch (Exception e) {
							throw new MakeATestException("Exception in execute processor",e);
						}
					}
				}		
			}			
		}
	}

	/**
	 * Faz o processamento do objeto para as anotações d tipo PROXYMETHOD
	 * @param object
	 * @param method
	 * @param args
	 * @param proxy
	 * @return
	 * @throws Throwable
	 */
	private Object execute(Object object, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		if(logger.isDebugEnabled()){logger.debug("Execute process");}
		boolean isExecuted = false;
		Object invoked = null;
		Map<Object,List<AnnotationProperties>> properties = this.metadataReader.getContainer().getProperties(MakeATestScope.PROXYMETHOD);
		if(logger.isDebugEnabled() && properties != null){logger.debug("Properties size: " + properties.size());}
		if(properties != null && properties.containsKey(method)){
			List<AnnotationProperties> props = properties.get(method);
			if(props != null) {
				for (AnnotationProperties annotationProperties : props) {
					if(logger.isDebugEnabled()){logger.debug("Process the annotation: " + annotationProperties.getAnnotation());}
					AnnotationProcessor annotationProcessor = annotationProperties.getProcessor(); 

					HashSet<MakeATestProxyBehavior> enums = new HashSet<MakeATestProxyBehavior>();
					for(MakeATestProxyBehavior makeATestActionEnum : annotationProperties.getActions()){
						enums.add(makeATestActionEnum);
					}
					
					if(enums.contains(MakeATestProxyBehavior.BEFORE) && enums.contains(MakeATestProxyBehavior.AFTER)) {
						try {
							annotationProcessor.setProxyBehavior(MakeATestProxyBehavior.BEFORE);
							annotationProcessor.process(this.instance);
							invoked = method.invoke(object, args);
							annotationProcessor.setProxyBehavior(MakeATestProxyBehavior.AFTER);
							annotationProcessor.process(this.instance);
							isExecuted = true;
						} catch (InvocationTargetException ite) {
							throwInvocationTargetException(object, ite);
						} catch (Exception e) {
							MakeATestException makeATestException = new MakeATestException("Exception in processor class: " + e);
							makeATestException.setStackTrace(e.getStackTrace());
							throw makeATestException;
						}						
					} else if(enums.contains(MakeATestProxyBehavior.BEFORE)) {
						try {
							annotationProcessor.setProxyBehavior(MakeATestProxyBehavior.BEFORE);
							annotationProcessor.process(this.instance);
							invoked = method.invoke(object, args);
							isExecuted = true;
						} catch (InvocationTargetException ite) {
							throwInvocationTargetException(object, ite);
						} catch (Exception e) {
							MakeATestException makeATestException = new MakeATestException("Exception in processor class: " + e);
							makeATestException.setStackTrace(e.getStackTrace());
							throw makeATestException;
						}
					} else if(enums.contains(MakeATestProxyBehavior.AFTER)) {
						try {
							invoked = method.invoke(object, args);
							annotationProcessor.setProxyBehavior(MakeATestProxyBehavior.AFTER);
							annotationProcessor.process(this.instance);
							isExecuted = true;
						} catch (InvocationTargetException ite) {
							throwInvocationTargetException(object, ite);
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
	 * Método para tratar as excessões de InvocationTargetException
	 * @param object - Objet invoke
	 * @param ite - InvocationTargetException
	 * @throws MakeATestException, MakeATestAssertionError 
	 */
	private void throwInvocationTargetException(Object object, InvocationTargetException ite) throws MakeATestException, MakeATestAssertionError {
		if(logger.isDebugEnabled()){logger.debug("Class " + ite.getCause().getClass());}
		if(logger.isDebugEnabled()){logger.debug("Super Class " + ite.getCause().getClass().getSuperclass());}
		if(ite.getCause().getClass().getSuperclass() != null && 
				ite.getCause().getClass().getSuperclass().equals(AssertionFailedError.class) || 
				ite.getCause().getClass().getSuperclass().equals(AssertionError.class)){
			MakeATestAssertionError assertionError = null;
			/**
			 * This verify is because the implementation of asserTrue add null in String.  
			 * 	static public void assertTrue(boolean condition) {
			 *		assertTrue(null, condition);
			 *	}
			 */
			if(ite.getCause().getMessage().equals("null")){
				if(logger.isDebugEnabled()){logger.debug("Cause is null");}
				assertionError = new MakeATestAssertionError(object.getClass().getSimpleName()+" Assert fail.");
			} else {
				if(logger.isDebugEnabled()){logger.debug("Cause not null: " + ite.getCause().getMessage());}
				assertionError = new MakeATestAssertionError(object.getClass().getSimpleName()+" fail " + ite.getCause().getMessage());
			}
			assertionError.setStackTrace(ite.getCause().getStackTrace());
			throw assertionError;		
		} else {
			MakeATestException makeATestException = new MakeATestException("Exception when invocation object " + object.getClass().getSimpleName() + ", the exception: " + ite.getCause().getMessage());
			makeATestException.setStackTrace(ite.getCause().getStackTrace());
			throw makeATestException;
		}
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
		if(logger.isDebugEnabled()){logger.debug("Intercept method");}
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
