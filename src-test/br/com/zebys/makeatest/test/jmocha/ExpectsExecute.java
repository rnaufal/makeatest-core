package br.com.zebys.makeatest.test.jmocha;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import br.com.zebys.makeatest.MakeATestFieldExecuteInterface;

public class ExpectsExecute implements MakeATestFieldExecuteInterface {
	public void execute(Annotation annotation, Field field, Object object) throws Exception {
		Expects expects = (Expects) annotation;
		Object objectMock = field.getType().newInstance();
		Method method = objectMock.getClass().getMethod(expects.expects(),String.class);
		method.invoke(objectMock, expects.returns());
		field.set(object, objectMock);
	}
}
