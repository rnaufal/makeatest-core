package br.com.zebys.makeatest.proxy;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MakeATestV2Proxy implements InvocationHandler {

	public static Object createProxy(Object obj) {
		return Proxy.newProxyInstance(obj.getClass().getClassLoader(), obj
				.getClass().getInterfaces(), new MakeATestV2Proxy(obj));
	}

	private Object obj;

	public MakeATestV2Proxy(Object obj) {
		this.obj = obj;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Annotation[] annotations = method.getDeclaredAnnotations();
		//System.out.println("metodo: " + method.getName());
		
		for (Annotation annotation : annotations) {
			
			try {
	            Class<? extends Annotation> type = annotation.annotationType();
	            Method[] methods = type.getMethods();
	            for (Method annotationMethod : methods) {
	            	
	                if (annotationMethod.getName().equals("klass")) {
	                    //Object defaultValue = annotationMethod.getDefaultValue();
	                	
	                    //Sei quem Ž a classe q implementa o executor
	                	Object actualValue  = annotationMethod.invoke(annotation);
	                	Class executorClasse = (Class)actualValue;
	                    Object executor = executorClasse.newInstance();
	                    Method execute = executorClasse.getMethod("execute", Annotation.class);	
	                    execute.invoke(executor, annotation);
	                }
	            }
	        }
			catch (InvocationTargetException e) {
	            throw e.getTargetException();
	        }
	        catch (Exception e) {
	            throw e;
	        }
		}
		
		return method.invoke(obj, args);
	}
}
