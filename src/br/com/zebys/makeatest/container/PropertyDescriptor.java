package br.com.zebys.makeatest.container;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class PropertyDescriptor {
	private Annotation makeATestConfig;
	private Method method;
	private Object object;

	public PropertyDescriptor(Annotation makeATestConfig, Method method, Object object) {
		this.makeATestConfig = makeATestConfig;
		this.method = method;
		this.object = object;
	}

	public Annotation getMakeATestConfig() {
		return makeATestConfig;
	}

	public void setMakeATestConfig(Annotation makeATestConfig) {
		this.makeATestConfig = makeATestConfig;
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
