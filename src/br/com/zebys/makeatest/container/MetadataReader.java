package br.com.zebys.makeatest.container;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import br.com.zebys.makeatest.annotations.MakeATestConfig;

/**
 * Reader do Container
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

	private void readMethodAnnotation(Method method) /*throws Throwable*/{
		Annotation[] annotations = method.getDeclaredAnnotations();

		for (Annotation annotation : annotations) {
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

			}
		}
	}

}
