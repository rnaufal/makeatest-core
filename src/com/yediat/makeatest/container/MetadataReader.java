package com.yediat.makeatest.container;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import com.yediat.makeatest.metadatareading.delegate.AnnotationReader;
import com.yediat.makeatest.metadatareading.delegate.DelegateReader;

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

	public MetadataContainer createContainer(Method method) {
		//System.out.println("Criando container: " + method.getName());
		// create
		this.container = new MetadataContainer();
		// populate
		readMethodAnnotation(method);
		return this.container;
	}

	public MetadataContainer getContainer() {
		return this.container;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void readMethodAnnotation(Method method) /*throws Throwable*/{
		Annotation[] annotations = method.getDeclaredAnnotations();

		//Implementação da parte de Delegate Metadata Reader 
		for (Annotation annotation : annotations) {
			if(annotation.annotationType().isAnnotationPresent(DelegateReader.class)){
				DelegateReader reader = (DelegateReader) annotation.annotationType().getAnnotation(DelegateReader.class);
				
				Class<? extends AnnotationReader> readerClass = reader.value();
				try {
					AnnotationReader annotationReader = readerClass.newInstance();
					PropertyDescriptor propertyDescriptor = new PropertyDescriptor();
					annotationReader.readAnnotation(annotation, propertyDescriptor);
					this.container.put(method, propertyDescriptor);
				} catch (Exception e) {
					throw new RuntimeException("cannot instanciate reader", e);
				}
			}
			
			/*
			
			if (annotation.annotationType().isAnnotationPresent(MakeATestConfig.class)) {
				MakeATestConfig makeATestConfig = annotation.annotationType().getAnnotation(MakeATestConfig.class);
				Class<?> executorClasse = (Class<?>) makeATestConfig.klass();
				try {
					Object executor = executorClasse.newInstance();
					Method execute = executorClasse.getMethod("execute", Annotation.class, Method.class, Object.class);
					this.container.put(method, new PropertyDescriptor(annotation, execute, executor));
				} catch (InstantiationException e) {
					e.printStackTrace(); //TODO
				} catch (IllegalAccessException e) {
					e.printStackTrace(); //TODO
				} catch (SecurityException e) {
					e.printStackTrace(); //TODO
				} catch (NoSuchMethodException e) {
					e.printStackTrace(); //TODO
				}
				
			}*/
			
			
			
		}
	}

}
