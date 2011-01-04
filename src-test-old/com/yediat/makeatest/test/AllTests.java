package com.yediat.makeatest.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses( { 	FileAnnotationTest.class,
						FieldAnnotationTest.class,
						MockAnnotationTest.class,
						CommentTest.class
})
public class AllTests {}
