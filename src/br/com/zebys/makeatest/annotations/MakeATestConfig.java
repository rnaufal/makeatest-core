package br.com.zebys.makeatest.annotations;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface MakeATestConfig {
	Class<?> klass();
}
