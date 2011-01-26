package com.yediat.makeatest.core.container;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import com.yediat.makeatest.core.MakeATestEnum;
import com.yediat.makeatest.core.MakeATestInitializationException;
import com.yediat.makeatest.core.metadata.processor.After;
import com.yediat.makeatest.core.metadata.processor.Before;
import com.yediat.makeatest.core.metadata.reading.MakeATestReader;
import com.yediat.makeatest.core.metadata.reading.MakeATestReaderInterface;

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
	
	public boolean contains(Method method) {
		return this.container.contains(method);
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void readAnnotations(Class<?> klass) throws MakeATestInitializationException {
		Method [] methods = klass.getMethods();
		for (Method method : methods) {
			Annotation[] annotations = method.getAnnotations();

			//Implementação da parte de Delegate Metadata Reader 
			for (Annotation annotation : annotations) {
				if(annotation.annotationType().isAnnotationPresent(MakeATestReader.class)){
					MakeATestReader reader = (MakeATestReader) annotation.annotationType().getAnnotation(MakeATestReader.class);
					Class<? extends MakeATestReaderInterface> readerClass = reader.value();
					try {
						MakeATestReaderInterface annotationReader = readerClass.newInstance();
						PropertyDescriptor propertyDescriptor = new PropertyDescriptor();
						annotationReader.readAnnotation(annotation, propertyDescriptor);
						setTypeProcessor(annotation, propertyDescriptor);
						this.container.put(method, propertyDescriptor);
					} catch (Exception e) {
						MakeATestInitializationException makeATestException = new MakeATestInitializationException("Connot initicalize readar: " + readerClass.getName(), e);
						makeATestException.setStackTrace(e.getStackTrace());
						throw makeATestException;
					}
				}
			}
		}
	}
	
	private void setTypeProcessor(Annotation annotation, PropertyDescriptor propertyDescriptor) {
		if(annotation.annotationType().isAnnotationPresent(After.class)){
			if(annotation.annotationType().isAnnotationPresent(Before.class)) {
				propertyDescriptor.setType(MakeATestEnum.PROCESS_BOTH);
			} else {
				propertyDescriptor.setType(MakeATestEnum.PROCESS_AFTER);
			}
		} else if(annotation.annotationType().isAnnotationPresent(Before.class)) {
			propertyDescriptor.setType(MakeATestEnum.PROCESS_BEFORE);
		} else {
			propertyDescriptor.setType(MakeATestEnum.PROCESS_AFTER);
		}
	}

}
