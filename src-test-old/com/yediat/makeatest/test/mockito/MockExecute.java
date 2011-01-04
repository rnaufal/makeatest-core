package com.yediat.makeatest.test.mockito;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.mockito.Mockito;

import com.yediat.makeatest.MakeATestExecuteInterface;


public class MockExecute implements MakeATestExecuteInterface {

	@Override
	public void execute(Annotation annotation, Field field, Object object)
			throws Exception {
		Object objectMock = Mockito.mock(field.getType());
		field.set(object, objectMock);
	}

	@Override
	public void execute(Annotation annotation, Method method, Object object)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
