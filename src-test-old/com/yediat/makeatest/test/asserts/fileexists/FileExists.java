package com.yediat.makeatest.test.asserts.fileexists;

import static java.lang.annotation.ElementType.METHOD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.yediat.makeatest.annotations.MakeATestConfig;


@MakeATestConfig(klass = FileExistsExecute.class)
@Target({METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface FileExists {
	String filePath();
}