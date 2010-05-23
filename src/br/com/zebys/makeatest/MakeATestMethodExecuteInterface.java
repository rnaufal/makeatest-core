package br.com.zebys.makeatest;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public interface MakeATestMethodExecuteInterface {
	public void execute(Annotation annotation, Method method, Object object) throws Exception;
}
