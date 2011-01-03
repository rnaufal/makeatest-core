package br.com.zebys.makeatest.container;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import br.com.zebys.makeatest.annotations.MakeATestConfig;

/**
 * Classe responsável pela leitura das anotações das classes, cuja as anotações contenham a anotação MakeATestConfig
 * 
 * @author deborachama
 *
 * @see MakeATestConfig
 * @see MetadataContainer
 * @see PropertyDescriptor
 *
 */
public class MetadataReader {
	private MetadataContainer container = null;

	/**
	 * Metodo para criar o Container para as anotações recuperadas dos métodos da classe dos testes unitários
	 * @param method Método executado e interceptado pelo proxy
	 * @return MetadataContainer
	 */
	public MetadataContainer createContainer(Method method) {
		//System.out.println("Criando container: " + method.getName());
		// create
		this.container = new MetadataContainer();
		// populate
		readMethodAnnotation(method);
		return this.container;
	}

	/**
	 * Recupera o metadata container criado, pode retornar NULL caso o createContainer não foi executado
	 * @return MetadataContainer
	 */
	public MetadataContainer getContainer() {
		return this.container;
	}

	/**
	 * Recupera as anotações do método interceptado para recuperar as anotações que estão anotadas com MakeATestConfig.
	 * Adiciona no container o PropertyDescriptor com a anotação, a classe reposponsável pelo processamento da anotação e o método "execute" dessa classe.
	 * @param method
	 */
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
