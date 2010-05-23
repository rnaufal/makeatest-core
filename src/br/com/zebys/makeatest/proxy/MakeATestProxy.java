package br.com.zebys.makeatest.proxy;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import br.com.zebys.makeatest.MakeATestFieldExecuteInterface;
import br.com.zebys.makeatest.MakeATestMethodExecuteInterface;
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
					this.invoke(annotation, field);
				}
			}
		}
	}
	
	private void methodAnnotations(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		Annotation[] annotations = method.getDeclaredAnnotations();
		
		for (Annotation annotation : annotations) {
			if(annotation.annotationType().isAnnotationPresent(MakeATestConfig.class)) {
				this.invoke(annotation, method);
			}			
		}	
	}
	
	private void invoke(Annotation annotation, AccessibleObject accessibleObject) throws Throwable {
		MakeATestConfig makeATestConfig = annotation.annotationType().getAnnotation(MakeATestConfig.class);
    	Class<?> executorClasse = (Class<?>) makeATestConfig.klass();
    	    	
    	if(accessibleObject.getClass().equals(Method.class)) {
    		this.invokeMethod(executorClasse, annotation, (Method) accessibleObject);
    	} else if(accessibleObject.getClass().equals(Field.class)) {
    		this.invokeField(executorClasse, annotation, (Field) accessibleObject);
    	} else {
    		throw new MakeATestException("The invoke for accessibleObject not implemented: " + accessibleObject.getClass().getCanonicalName());
    	}    	
	}

	private void invokeMethod(Class<?> executorClasse, Annotation annotation, Method method) throws Throwable {
    	try {
    		executorClasse.asSubclass(MakeATestMethodExecuteInterface.class);
    	} catch (Exception e) {
			throw new MakeATestException("The class executing not implements the " + MakeATestMethodExecuteInterface.class.getCanonicalName());
		}

    	Object executor = executorClasse.newInstance();
        Method execute = executorClasse.getMethod("execute", Annotation.class, Method.class, Object.class);
    	try {
        	execute.invoke(executor, annotation, method, this.object);
        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        } 
	}

	private void invokeField(Class<?> executorClasse, Annotation annotation, Field field) throws Throwable {
    	try {
    		executorClasse.asSubclass(MakeATestFieldExecuteInterface.class);
    	} catch (Exception e) {
			throw new MakeATestException("The class executing not implements the " + MakeATestMethodExecuteInterface.class.getCanonicalName());
		}

    	Object executor = executorClasse.newInstance();
        Method execute = executorClasse.getMethod("execute", Annotation.class, Field.class, Object.class);
    	try {
        	execute.invoke(executor, annotation, field, this.object);
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
