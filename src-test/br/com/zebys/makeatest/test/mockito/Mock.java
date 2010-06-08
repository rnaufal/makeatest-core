package br.com.zebys.makeatest.test.mockito;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.com.zebys.makeatest.annotations.MakeATestConfig;

@MakeATestConfig(klass = MockExecute.class)
@Target( { ElementType.FIELD, ElementType.LOCAL_VARIABLE })
@Retention(RetentionPolicy.RUNTIME)
public @interface Mock {

}
