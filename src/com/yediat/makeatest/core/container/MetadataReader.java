package com.yediat.makeatest.core.container;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.yediat.makeatest.core.MakeATestInitializationException;
import com.yediat.makeatest.core.metadata.reading.MakeATestProxyBehavior;
import com.yediat.makeatest.core.metadata.reading.MakeATestReader;
import com.yediat.makeatest.core.metadata.reading.MakeATestReaderInterface;
import com.yediat.makeatest.core.metadata.reading.MakeATestScope;

/**
 * Responsável pela leitura dos metadados (anotações) dos métodos e também pela criação do container das anotações
 * 
 * @author deborachama
 *
 */
public class MetadataReader {
	
	private MetadataContainer container = null;
	
	public MetadataReader(Class<?> klass) throws MakeATestInitializationException {
		this.container = new MetadataContainer();
		this.stetament(klass);
	}
	
	public MetadataContainer getContainer() {
		return this.container;
	}
	
	private void stetament(Class<?> klass) throws MakeATestInitializationException {
		stetamentClass(klass);
		stetamentFields(klass);
		stetamentMethods(klass);
	}
	
	private void stetamentClass(Class<?> klass) throws MakeATestInitializationException {
		this.processAnnotations(klass.getAnnotations(), klass);
	}
	
	private void stetamentFields(Class<?> klass) throws MakeATestInitializationException {
		Field [] fields = klass.getDeclaredFields();
		for (Field field : fields) {
			this.processAnnotations(field.getAnnotations(), field);
		}
	}
	
	private void stetamentMethods(Class<?> klass) throws MakeATestInitializationException {
		Method [] methods = klass.getMethods();
		for (Method method : methods) {
			this.processAnnotations(method.getAnnotations(), method);
		}		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void processAnnotations(Annotation[] annotations, Object key) throws MakeATestInitializationException {
		for (Annotation annotation : annotations) {
			if(annotation.annotationType().isAnnotationPresent(MakeATestReader.class)){
								
				MakeATestReader reader = (MakeATestReader) annotation.annotationType().getAnnotation(MakeATestReader.class);
				
				MakeATestProxyBehavior [] proxyBehavior = reader.proxyBehavior();
				MakeATestScope scope = reader.scope();
				
				AnnotationProperties annotationProperties = new AnnotationProperties();
				
				Class<? extends MakeATestReaderInterface> readerClass = reader.reader();
				try {
					MakeATestReaderInterface annotationReader = readerClass.newInstance();
					
					annotationProperties.setProxyBehabior(proxyBehavior);
					annotationProperties.setAnnotated(key);
					annotationProperties.setAnnotation(annotation);
					
					annotationReader.readAnnotation(annotation, annotationProperties);
					this.container.put(scope, key, annotationProperties);
				} catch(ClassCastException ce) {
					String type = reader.reader().getGenericInterfaces()[0].toString();
					Matcher m = Pattern.compile(".*<.*\\.(.*)>.*").matcher(type);
					m.matches();
					MakeATestInitializationException makeATestException = new MakeATestInitializationException("Class Cast Exception in call method readAnnotation(...) in reader class: \n Cast Error: \"(" + m.group(1) + ") " + annotation.annotationType().getSimpleName() + "\" \n Reader Class: " + readerClass.getName(), ce);
					makeATestException.setStackTrace(ce.getStackTrace());
					throw makeATestException;					
				} catch (Exception e) {
					MakeATestInitializationException makeATestException = new MakeATestInitializationException("Cannot initialize reader: " + readerClass.getName(), e);
					makeATestException.setStackTrace(e.getStackTrace());
					throw makeATestException;
				}
			}
		}		
	}
}
