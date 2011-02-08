package com.yediat.makeatest.test;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.yediat.makeatest.annotations.testexception.WithoutProcessor;
import com.yediat.makeatest.core.MakeATestAssertionError;
import com.yediat.makeatest.junit.MakeATestRunner;


@RunWith(MakeATestRunner.class)
public class WithoutProcessorTest {

	@Test(expected=MakeATestAssertionError.class)
	@WithoutProcessor
	public void test() {
	}
	
}
