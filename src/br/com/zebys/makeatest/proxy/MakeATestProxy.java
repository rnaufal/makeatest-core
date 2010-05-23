package br.com.zebys.makeatest.proxy;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import br.com.zebys.makeatest.MakeATestExecuteInterface;
import br.com.zebys.makeatest.annotations.MakeATestConfig;
import br.com.zebys.makeatest.exception.MakeATestException;

public class MakeATestProxy implements MethodInterceptor {

	private Object object;
	
	private MakeATestProxy(Object object) {
		this.object = object;
	}
	
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		this.methodAnnotations(obj, method, args, proxy);
		this.fieldAnnotation(obj, method, args, proxy);
		return method.invoke(this.object, args);
	}
	
	private void fieldAnnotation(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		Class<?> klass = this.object.getClass();
		Field [] fields = klass.getDeclaredFields();
		
		for (Field field : fields) {
			Annotation[] annotations = field.getDeclaredAnnotations();
			for (Annotation annotation : annotations) {
				if(annotation.annotationType().isAnnotationPresent(MakeATestConfig.class)) {
					this.invoke(annotation);
				}
			}
		}
	}
	
	private void methodAnnotations(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		Annotation[] annotations = method.getDeclaredAnnotations();
		
		for (Annotation annotation : annotations) {
			if(annotation.annotationType().isAnnotationPresent(MakeATestConfig.class)) {
				this.invoke(annotation);
			}			
		}	
	}
	
	private void invoke(Annotation annotation) throws Throwable {
		MakeATestConfig makeATestConfig = annotation.annotationType().getAnnotation(MakeATestConfig.class);
    	Class<?> executorClasse = (Class<?>) makeATestConfig.klass();
    	
    	try {
    		executorClasse.asSubclass(MakeATestExecuteInterface.class);
    	} catch (Exception e) {
			throw new MakeATestException("The class executing not implements the " + MakeATestExecuteInterface.class.getCanonicalName());
		}
    	
    	Object executor = executorClasse.newInstance();
        Method execute = executorClasse.getMethod("execute", Annotation.class);
        
    	try {
        	execute.invoke(executor, annotation);
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        } 
	}
	
	@SuppressWarnings("unchecked")
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
