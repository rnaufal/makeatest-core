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
	ProxyMethodClassCastExceptionInReaderTest.class
})
public class AllTests {}
