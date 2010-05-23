package br.com.zebys.makeatest;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public interface MakeATestFieldExecuteInterface {
	public void execute(Annotation annotation, Field field, Object object) throws Exception;
}
