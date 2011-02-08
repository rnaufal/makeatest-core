package com.yediat.makeatest.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses( { 	FileExistsAnnotationTest.class,
						FileExistsAssertErrorTest.class,
						FileExistsExceptionInProcessorTest.class,
						FileExistsExceptionInReaderTest.class,
						FileNotExistsAnnotationTest.class,
						RequiredFileAnnotationTest.class,
						ObjectLoadTest.class,
						SystemPropertyLoadTest.class,
						WithoutProcessorTest.class
})
public class ExecuteAllTests {}
