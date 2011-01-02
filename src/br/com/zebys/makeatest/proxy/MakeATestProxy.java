package br.com.zebys.makeatest.proxy;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import br.com.zebys.makeatest.annotations.MakeATestConfig;
import br.com.zebys.makeatest.container.MetadataReader;
import br.com.zebys.makeatest.container.PropertyDescriptor;

//FrameworkController
/**
 * Essa classe implementa o MethodInterceptor e cria um proxy para um objeto. Dessa forma é possível interceptar todas as chamadas feita para o objeto real.
 * Assim nesta mesma classe é feita a criação do MetadataReader (responsável por recuperar as informações das anotações da classe), e a execução
 * referente a cada anotação...
 * 
 * @see MetadataReader
 * 
 */
public class MakeATestProxy implements MethodInterceptor {

	private Object object;
	protected MetadataReader reader;
	
	/**
	 * Construtor responsável por receber o objeto que representa a instância da classe de testes
	 * e criar uma instância do MetadataReader.
	 * 
	 * @param object
	 */
	private MakeATestProxy(Object object) {
		this.object = object;
		this.reader = new MetadataReader();
	}

	/**
	 * Método que intercepta as chamadas para os métodos da classe de teste
	 * 
	 * @param obj Instância da classe de teste
	 * @param method Method que está sendo chamado
	 * @param args Argumentos passados para o método
	 * @param proxy O proxy do método
	 */
	//execute do pattern MetadataContainer
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		this.reader.createContainer(method);
		this.methodAnnotation(method);
		this.fieldAnnotation();
		return method.invoke(this.object, args);
	}
	
	/**
	 * Método responsável por executar as anotações do tipo TYPE (class, interface or enum declaration)
	 * @throws Throwable
	 */
	private void fieldAnnotation() throws Throwable {
		Class<?> klass = this.object.getClass();
		Field [] fields = klass.getDeclaredFields();
		
		for (Field field : fields) {
			Annotation[] annotations = field.getDeclaredAnnotations();
			for (Annotation annotation : annotations) {
				if(annotation.annotationType().isAnnotationPresent(MakeATestConfig.class)) {
					MakeATestConfig makeATestConfig = annotation.annotationType().getAnnotation(MakeATestConfig.class);
			    	Class<?> executorClasse = (Class<?>) makeATestConfig.klass();
			    	Object executor = executorClasse.newInstance();
			        Method execute = executorClasse.getMethod("execute", Annotation.class, Field.class, Object.class);
			    	try {
			        	execute.invoke(executor, annotation, field, this.object);
			        } catch (InvocationTargetException e) {
			            throw e.getTargetException();
			        }
				}
			}
		}
	}
	
	/**
	 * Método responsável por executar as anotações do tipo METHOD.
	 * 
	 * @param method Método para ser verificado a existência de anotações
	 * @throws Throwable
	 */
	private void methodAnnotation(Method method) throws Throwable {
		List<PropertyDescriptor> props = this.reader.getContainer().getProperties(method);
		if (props != null) { //TODO verificar nulo ou retornar lista vazia no metodo acima?
			
			for (PropertyDescriptor propertyDescriptor : props) {
				Annotation annotation = propertyDescriptor.getAnnotation();
				MakeATestConfig makeATestConfig = annotation.annotationType().getAnnotation(MakeATestConfig.class);
		    	Class<?> executorClasse = (Class<?>) makeATestConfig.klass();
		    	Object executor = executorClasse.newInstance();
		        Method execute = executorClasse.getMethod("execute", Annotation.class, Method.class, Object.class);
		    	try {
		        	execute.invoke(executor, annotation, method, this.object);
		        } catch (InvocationTargetException e) {
		            throw e.getTargetException();
		        } 
			}
		}
		
	}

	@SuppressWarnings("unchecked")
	/**
	 * Método responsável em criar um proxy a partir de um objeto possíbilidando interceptar todas as chamadas para esse objeto
	 * @param object Objeto passado para ser criado o proxy e interceptar todas as chamadas para esse objeto
	 */
	public static <E> E getProxy(E object) {
		try {
			MakeATestProxy proxy = new MakeATestProxy(object);
			Enhancer e = new Enhancer();
			e.setSuperclass(object.getClass());
			e.setCallback(proxy);
			return (E) e.create();
		} catch (Throwable e) {
			throw new Error(e.getMessage());
		}
	}

}
