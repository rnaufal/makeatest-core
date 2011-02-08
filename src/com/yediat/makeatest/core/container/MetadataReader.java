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
		readClass(klass);
		readFields(klass);
		readMethods(klass);
	}
	
	private void readClass(Class<?> klass) throws MakeATestInitializationException {
		this.processAnnotations(klass.getAnnotations(), klass);
	}
	
	private void readFields(Class<?> klass) throws MakeATestInitializationException {
		Field [] fields = klass.getDeclaredFields();
		for (Field field : fields) {
			this.processAnnotations(field.getAnnotations(), field);
		}
	}
	
	private void readMethods(Class<?> klass) throws MakeATestInitializationException {
		Method [] methods = klass.getMethods();
		for (Method method : methods) {
			this.processAnnotations(method.getAnnotations(), method);
		}		
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void processAnnotations(Annotation[] annotations, Object key) throws MakeATestInitializationException {
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
					annotationProperties.setAnnotated(key);
					
					annotationReader.readAnnotation(annotation, annotationProperties);
					this.container.put(makeATestScope.value(), key, annotationProperties);
				} catch (Exception e) {
					MakeATestInitializationException makeATestException = new MakeATestInitializationException("Connot initialize reader: " + readerClass.getName(), e);
					makeATestException.setStackTrace(e.getStackTrace());
					throw makeATestException;
				}
			}
		}		
	}
}
