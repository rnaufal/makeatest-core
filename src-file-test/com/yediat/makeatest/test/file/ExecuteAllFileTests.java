package com.yediat.makeatest.test.file;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses( { 	CreateFileTest.class,
						DeleteFileTest.class
})
public class ExecuteAllFileTests {}
