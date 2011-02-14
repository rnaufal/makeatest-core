package com.yediat.makeatest.core.container;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.yediat.makeatest.core.MakeATestInitializationException;
import com.yediat.makeatest.core.metadata.reading.MakeATestActionEnum;
import com.yediat.makeatest.core.metadata.reading.MakeATestReader;
import com.yediat.makeatest.core.metadata.reading.MakeATestReaderInterface;
import com.yediat.makeatest.core.metadata.reading.MakeATestScopeEnum;

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
				
				MakeATestActionEnum [] actions = reader.actions();
				MakeATestScopeEnum scope = reader.scope();
				
				Class<? extends MakeATestReaderInterface> readerClass = reader.value();
				try {
					MakeATestReaderInterface annotationReader = readerClass.newInstance();
					
					AnnotationProperties annotationProperties = new AnnotationProperties();
					annotationProperties.setActions(actions);
					annotationProperties.setAnnotated(key);
					
					annotationReader.readAnnotation(annotation, annotationProperties);
					this.container.put(scope, key, annotationProperties);
				} catch (Exception e) {
					MakeATestInitializationException makeATestException = new MakeATestInitializationException("Connot initialize reader: " + readerClass.getName(), e);
					makeATestException.setStackTrace(e.getStackTrace());
					throw makeATestException;
				}
			}
		}		
	}
}
