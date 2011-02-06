package com.yediat.makeatest.fileverify;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.yediat.makeatest.core.metadata.reading.MakeATestReader;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@MakeATestReader(ObjectLoadReader.class)
public @interface ObjectLoad {
	String value();
}
