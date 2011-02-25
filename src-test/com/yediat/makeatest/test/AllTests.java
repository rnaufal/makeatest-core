package com.yediat.makeatest.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses( {
	LoadVariableInObjectTest.class,
	LoadVariableInObjectWithoutProcessTest.class,
	LoadWithExceptionInProcessorTest.class,
	LoadWithExceptionInReaderTest.class,
	ProxyMethodBeforeTest.class,
	ProxyMethodAfterTest.class,
	ProxyMethodBeforeAfterTest.class,
	ProxyMethodClassCastExceptionInReaderTest.class,
	ProxyMethodWithTwoAnnotationTest.class,
	ProxyMethodInvokeErrorWithAssertTest.class
})
public class AllTests {}
