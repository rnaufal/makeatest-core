package com.yediat.makeatest.file;

import static java.lang.annotation.ElementType.METHOD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.yediat.makeatest.core.metadata.processor.After;
import com.yediat.makeatest.core.metadata.reading.MakeATestReader;

@Target({ METHOD })
@Retention(RetentionPolicy.RUNTIME)
@MakeATestReader(FileContainsReader.class)
@After
public @interface FileContains {
	String name();
	String content();
}