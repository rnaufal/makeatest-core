package com.yediat.makeatest.file;

import static java.lang.annotation.ElementType.METHOD;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.yediat.makeatest.core.metadata.processor.Before;
import com.yediat.makeatest.core.metadata.reading.MakeATestReader;

@Target({ METHOD })
@Retention(RetentionPolicy.RUNTIME)
@MakeATestReader(CreateFileReader.class)
@Before
public @interface CreateFile {
	String name();
	String content();
}