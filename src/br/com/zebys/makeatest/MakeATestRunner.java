package br.com.zebys.makeatest;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

import br.com.zebys.makeatest.annotations.Configure;

public class MakeATestRunner extends BlockJUnit4ClassRunner {

	public MakeATestRunner(Class<?> klass) throws InitializationError {
		super(klass);
		
		Method [] methods = klass.getMethods();
		for (Method method : methods) {
			
		}
		
	}

}
