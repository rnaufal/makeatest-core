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
 * Essa classe implementa o MethodInterceptor e cria um proxy para um objeto. Dessa forma � poss�vel interceptar todas as chamadas feita para o objeto real.
 * Assim nesta mesma classe � feita a cria��o do @see MetadataReader (respons�vel por recuperar as informa��es das anota��es da classe), e a execu��o
 * referente a cada anota��o.  
 */
public class MakeATestProxy implements MethodInterceptor {

	private Object object;
	protected MetadataReader reader;
	
	/**
	 * Contrutor respons�vel por receber o objeto que representa a instancia da classe de testes
	 * e criar uma instancia do MetadataReader.
	 * 
	 * @param object
	 */
	private MakeATestProxy(Object object) {
		this.object = object;
		this.reader = new MetadataReader();
	}

	/**
	 * M�todo que intercepta as chamadas para os metodos da classe de test
	 * 
	 * @param obj Instancia da classe de teste
	 * @param method Method que est� sendo chamado
	 * @param args Argumentos passados para o m�todo
	 * @param proxy O proxy do m�todo
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
	 * M�todo respons�vel por executar as anota��es do tipo TYPE (class, interface or enum declaration)
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
	 * M�todo respons�vel por executar as anota��es do tipo METHOD.
	 * 
	 * @param method M�todo para ser verificado a existencia de anota��es
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
	 * M�todo respons�vel em criar um proxy a partir de um objeto poss�bilidando interceptar todas as chamadas para esse objeto
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
