package com.yediat.makeatest.container;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Container
 * @author deborachama
 *
 */
public class PropertyDescriptor {
	private Annotation annotation;
	private Method method;
	private Object object;

	public PropertyDescriptor(Annotation annotation, Method method, Object object) {
		this.annotation = annotation;
		this.method = method;
		this.object = object;
	}

	public Annotation getAnnotation() {
		return annotation;
	}

	public void setAnnotation(Annotation annotation) {
		this.annotation = annotation;
	}

	public Method getMethod() {
		return method;
	}

	public void setMethod(Method method) {
		this.method = method;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}
	

}
