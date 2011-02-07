package com.yediat.makeatest.core.container;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.yediat.makeatest.core.MakeATestInitializationException;
import com.yediat.makeatest.core.metadata.reading.MakeATestExecution;
import com.yediat.makeatest.core.metadata.reading.MakeATestReader;
import com.yediat.makeatest.core.metadata.reading.MakeATestReaderInterface;
import com.yediat.makeatest.core.metadata.reading.MakeATestScope;

//TODO Verificar se essa classe precisa ser abstrata ou não. Se formos considerar a leitura de mais de uma 
// fonte de dados é interessante refatorá-la

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
		this.readAnnotations(klass);
	}
	
	public MetadataContainer getContainer() {
		return this.container;
	}
	
	private void readAnnotations(Class<?> klass) throws MakeATestInitializationException {
		readFields(klass);
		readMethods(klass);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void readFields(Class<?> klass) throws MakeATestInitializationException {
		Field [] fields = klass.getDeclaredFields();
		for (Field field : fields) {
			Annotation [] annotations = field.getAnnotations();
			for (Annotation annotation : annotations) {
				if(annotation.annotationType().isAnnotationPresent(MakeATestReader.class)){
					
					MakeATestExecution makeATestExecution = null;
					if(annotation.annotationType().isAnnotationPresent(MakeATestExecution.class)) {
						makeATestExecution = (MakeATestExecution) annotation.annotationType().getAnnotation(MakeATestExecution.class);
					} else {
						throw new MakeATestInitializationException("The annotation \""+annotation.annotationType().getCanonicalName()+"\" not contain the MakeATestExecution annotation.");
					}
					
					MakeATestScope makeATestScope = null;
					if(annotation.annotationType().isAnnotationPresent(MakeATestScope.class)) {
						makeATestScope = (MakeATestScope) annotation.annotationType().getAnnotation(MakeATestScope.class);
					} else {
						throw new MakeATestInitializationException("The annotation \""+annotation.annotationType().getCanonicalName()+"\" not contain the MakeATestScope annotation.");
					}
					
					MakeATestReader reader = (MakeATestReader) annotation.annotationType().getAnnotation(MakeATestReader.class);
					Class<? extends MakeATestReaderInterface> readerClass = reader.value();
					try {
						MakeATestReaderInterface annotationReader = readerClass.newInstance();
						
						AnnotationProperties annotationProperties = new AnnotationProperties();
						annotationProperties.setAnnotated(field);
						annotationProperties.setExecution(makeATestExecution.value());
						
						annotationReader.readAnnotation(annotation, annotationProperties);
						this.container.put(makeATestScope.value(), field, annotationProperties);
					} catch (Exception e) {
						MakeATestInitializationException makeATestException = new MakeATestInitializationException("Connot initialize reader: " + readerClass.getName(), e);
						makeATestException.setStackTrace(e.getStackTrace());
						throw makeATestException;
					}
				}
			}
		}
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void readMethods(Class<?> klass) throws MakeATestInitializationException {
		Method [] methods = klass.getMethods();
		for (Method method : methods) {
			Annotation[] annotations = method.getAnnotations();
			for (Annotation annotation : annotations) {
				if(annotation.annotationType().isAnnotationPresent(MakeATestReader.class)){
					
					MakeATestExecution makeATestExecution = null;
					if(annotation.annotationType().isAnnotationPresent(MakeATestExecution.class)) {
						makeATestExecution = (MakeATestExecution) annotation.annotationType().getAnnotation(MakeATestExecution.class);
					} else {
						throw new MakeATestInitializationException("The annotation \""+annotation.annotationType().getCanonicalName()+"\" not contain the MakeATestExecution annotation.");
					}
					
					MakeATestScope makeATestScope = null;
					if(annotation.annotationType().isAnnotationPresent(MakeATestScope.class)) {
						makeATestScope = (MakeATestScope) annotation.annotationType().getAnnotation(MakeATestScope.class);
					} else {
						throw new MakeATestInitializationException("The annotation \""+annotation.annotationType().getCanonicalName()+"\" not contain the MakeATestScope annotation.");
					}
					
					MakeATestReader reader = (MakeATestReader) annotation.annotationType().getAnnotation(MakeATestReader.class);
					Class<? extends MakeATestReaderInterface> readerClass = reader.value();
					try {
						MakeATestReaderInterface annotationReader = readerClass.newInstance();
						
						AnnotationProperties annotationProperties = new AnnotationProperties();
						annotationProperties.setExecution(makeATestExecution.value());
						annotationProperties.setAnnotated(method);
						
						annotationReader.readAnnotation(annotation, annotationProperties);
						this.container.put(makeATestScope.value(), method, annotationProperties);
					} catch (Exception e) {
						MakeATestInitializationException makeATestException = new MakeATestInitializationException("Connot initialize reader: " + readerClass.getName(), e);
						makeATestException.setStackTrace(e.getStackTrace());
						throw makeATestException;
					}
				}
			}
		}		
	}
}
