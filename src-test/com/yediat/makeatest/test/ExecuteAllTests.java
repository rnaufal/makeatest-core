package com.yediat.makeatest.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses( { 	FileExistsAnnotationTest.class,
						FileNotExistsAnnotationTest.class
})
public class ExecuteAllTests {}