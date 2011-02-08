package com.yediat.makeatest.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

import com.yediat.makeatest.annotations.systemproperty.SystemPropertyLoad;
import com.yediat.makeatest.junit.MakeATestRunner;


@RunWith(MakeATestRunner.class)

public class SystemPropertyLoadMultTest {

	@Test
	@SystemPropertyLoad(key="key1",value="value1")
	public void test1() {
		assertEquals("value1", System.getProperty("key1"));
	}

	@Test
	@SystemPropertyLoad(key="key2",value="value2")
	public void test2() {
		assertEquals("value1", System.getProperty("key1"));
		assertEquals("value2", System.getProperty("key2"));
	}

}
