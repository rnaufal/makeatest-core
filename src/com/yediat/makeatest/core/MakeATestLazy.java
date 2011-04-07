package com.yediat.makeatest.core;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yediat.makeatest.core.container.AnnotationProperties;
import com.yediat.makeatest.core.container.MetadataReader;
import com.yediat.makeatest.core.metadata.processor.AnnotationProcessor;
import com.yediat.makeatest.core.metadata.reading.MakeATestProxyBehavior;
import com.yediat.makeatest.core.metadata.reading.MakeATestScope;

public class MakeATestLazy {
	
	final Logger logger = LoggerFactory.getLogger(MakeATestLazy.class);
	private Object instance;
	private MetadataReader metadataReader;
	
	public void aspect(ProceedingJoinPoint jp) throws Throwable {
		if(logger.isDebugEnabled()){logger.debug("ProceedingJoinPoint: " + jp);}
		logger.debug("JoinPoint Aspect with kind: " + jp.getKind());
		logger.debug("Execution metho with annotation: " + jp.getSignature().getName());
		this.instance = jp.getTarget();
		if(this.instance == null){
			logger.debug("Static class: " + this.getClass().getClassLoader().loadClass(jp.getStaticPart().getSignature().getDeclaringTypeName()));
			this.metadataReader = new MetadataReader(this.getClass().getClassLoader().loadClass(jp.getStaticPart().getSignature().getDeclaringTypeName()));
		} else {
			logger.debug("Target class: " + jp.getTarget().getClass().getName());
			this.metadataReader = new MetadataReader(this.instance.getClass());
		}
		
		this.load();
		this.process(jp);
	}
		
	public void load() throws MakeATestException {
		if(logger.isDebugEnabled()){logger.debug("load()");}
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
	
	public void process(ProceedingJoinPoint jp) throws Throwable {
		if(logger.isDebugEnabled()){logger.debug("ProceedingJoinPoint: " + jp);}
		Method method = getJoinpointMethod(jp);
		boolean isExecuted = false; 
		Map<Object,List<AnnotationProperties>> properties = this.metadataReader.getContainer().getProperties(MakeATestScope.PROXYMETHOD);
		if(properties != null && properties.containsKey(method)){
			List<AnnotationProperties> props = properties.get(method);
			if(props != null) {
				for (AnnotationProperties annotationProperties : props) {
					AnnotationProcessor annotationProcessor = annotationProperties.getProcessor(); 

					HashSet<MakeATestProxyBehavior> enums = new HashSet<MakeATestProxyBehavior>();
					for(MakeATestProxyBehavior makeATestActionEnum : annotationProperties.getActions()){
						enums.add(makeATestActionEnum);
					}
					
					if(enums.contains(MakeATestProxyBehavior.BEFORE) && enums.contains(MakeATestProxyBehavior.AFTER)) {
						try {
							annotationProcessor.setProxyBehavior(MakeATestProxyBehavior.BEFORE);
							annotationProcessor.process(this.instance);
							jp.proceed();
							annotationProcessor.setProxyBehavior(MakeATestProxyBehavior.AFTER);
							annotationProcessor.process(this.instance);
							isExecuted = true;
						} catch (Exception e) {
							MakeATestException makeATestException = new MakeATestException("Exception in processor class: " + e);
							makeATestException.setStackTrace(e.getStackTrace());
							throw makeATestException;
						}						
					} else if(enums.contains(MakeATestProxyBehavior.BEFORE)) {
						try {
							annotationProcessor.setProxyBehavior(MakeATestProxyBehavior.BEFORE);
							annotationProcessor.process(this.instance);
							jp.proceed();
							isExecuted = true;
						} catch (Exception e) {
							MakeATestException makeATestException = new MakeATestException("Exception in processor class: " + e);
							makeATestException.setStackTrace(e.getStackTrace());
							throw makeATestException;
						}
					} else if(enums.contains(MakeATestProxyBehavior.AFTER)) {
						try {
							jp.proceed();
							annotationProcessor.setProxyBehavior(MakeATestProxyBehavior.AFTER);
							annotationProcessor.process(this.instance);
							isExecuted = true;
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
				jp.proceed();
			} catch (InvocationTargetException e) {
				throw e.getCause();
			}			
		}
	}
	
	
	private Method getJoinpointMethod(JoinPoint jp) throws MakeATestException, ClassNotFoundException {
		Class<?> c = null;
		if(jp.getTarget() == null){
			c = this.getClass().getClassLoader().loadClass(jp.getStaticPart().getSignature().getDeclaringTypeName());
		} else {
			c = jp.getTarget().getClass();
		}
        for (Method m : c.getMethods()) {
            if (m.getName().equals(jp.getSignature().getName())) {
                if (argumentsMatch(m, jp.getArgs())) {
                    return m;
                }
            }
        }
        throw new MakeATestException("Method in JoinPoint not found");
    }

    private boolean argumentsMatch(Method m, Object [] args) {
        if (args.length != m.getParameterTypes().length) {
            return false;
        }
        for (int i = 0; i < args.length; i++) {
            if (!m.getParameterTypes()[i].isInstance(args[i])) {
                return false;
            }
        }
        return true;
    }
}
