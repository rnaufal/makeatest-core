package br.com.zebys.makeatest.test.jmocha;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.com.zebys.makeatest.annotations.MakeATestConfig;

@MakeATestConfig(klass = ExpectsExecute.class)
@Target( { ElementType.FIELD, ElementType.LOCAL_VARIABLE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Expects {
	String expects();
	String returns();
}
