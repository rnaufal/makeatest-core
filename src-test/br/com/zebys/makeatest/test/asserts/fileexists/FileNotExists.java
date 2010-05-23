package br.com.zebys.makeatest.test.asserts.fileexists;

import static java.lang.annotation.ElementType.METHOD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import br.com.zebys.makeatest.annotations.MakeATestConfig;

@MakeATestConfig(klass = FileNotExistsExecute.class)
@Target({METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FileNotExists {
	String filePath();
}
