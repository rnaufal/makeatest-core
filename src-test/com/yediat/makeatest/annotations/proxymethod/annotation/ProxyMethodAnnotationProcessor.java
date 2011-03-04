package com.yediat.makeatest.annotations.proxymethod.annotation;

import java.lang.reflect.Field;

import com.yediat.makeatest.annotations.FailType;
import com.yediat.makeatest.core.MakeATestAssertionError;
import com.yediat.makeatest.core.metadata.processor.AnnotationProcessor;

public class ProxyMethodAnnotationProcessor extends AnnotationProcessor {

	private FailType failType;
	private String variable;
	private String text;
	
	public ProxyMethodAnnotationProcessor(String variable, String text, FailType failType) {
		this.failType = failType;
		this.variable = variable;
		this.text = text;
	}
	
	@Override
	public void process(Object instance) throws MakeATestAssertionError {
		if(this.failType != null && this.variable != null & this.text != null){
			if(this.failType.equals(FailType.PROCESSOR)){
				new Integer("a");
			}
			try {
				Field field = instance.getClass().getDeclaredField(this.variable);
				String textField = (String) field.get(instance);
				if(!this.text.equals(textField)){
					throw new MakeATestAssertionError("The text <"+this.text+">. not equals with <"+textField+">.");
				}
			} catch (SecurityException e) {
				throw new MakeATestAssertionError("The field not accessible.");
			} catch (NoSuchFieldException e) {
				throw new MakeATestAssertionError("The field not exist in class " + instance.getClass().getName() + ".");
			} catch (IllegalArgumentException e) {
				throw new MakeATestAssertionError(e);
			} catch (IllegalAccessException e) {
				throw new MakeATestAssertionError(e);
			}			
		}
	}

}
