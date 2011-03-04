package com.yediat.makeatest.annotations.proxymethod.annotation;

import java.lang.reflect.Field;

import com.yediat.makeatest.annotations.FailType;
import com.yediat.makeatest.core.MakeATestAssertionError;
import com.yediat.makeatest.core.metadata.processor.AnnotationProcessor;
import com.yediat.makeatest.core.metadata.reading.MakeATestProxyBehavior;

public class ProxyMethodBeforeAfterAnnotationProcessor extends AnnotationProcessor {

	private String variable;
	private String beforeText;
	private String afterText;
	private FailType failType;
	
	public ProxyMethodBeforeAfterAnnotationProcessor(String variable, String beforeText, String afterText, FailType failType) {
		this.variable = variable;
		this.beforeText = beforeText;
		this.afterText = afterText;
		this.failType = failType;
	}

	@Override
	public void process(Object instance) throws MakeATestAssertionError {
		if(this.failType != null && this.variable != null & this.beforeText != null){
			if(this.failType.equals(FailType.PROCESSOR)){
				new Integer("a");
			}
			try {
				Field field = instance.getClass().getDeclaredField(this.variable);
				String textField = (String) field.get(instance);
				if(makeATestProxyBehavior.equals(MakeATestProxyBehavior.BEFORE)){
					if(!this.beforeText.equals(textField)){
						throw new MakeATestAssertionError("The text <"+this.beforeText+">. not equals with <"+textField+">.");
					}					
				} else if(makeATestProxyBehavior.equals(MakeATestProxyBehavior.AFTER)){
					if(!this.afterText.equals(textField)){
						throw new MakeATestAssertionError("The text <"+this.afterText+">. not equals with <"+textField+">.");
					}										
				} else {
					throw new MakeATestAssertionError("Fail.");
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
