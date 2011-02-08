package com.yediat.makeatest.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.yediat.makeatest.annotations.systemproperty.SystemPropertyLoad;
import com.yediat.makeatest.junit.MakeATestRunner;

@RunWith(MakeATestRunner.class)
@SystemPropertyLoad(key="file.props",value="./system.properties")
public class SystemPropertyLoadTest {

	@BeforeClass
	public static void beforeClass() {
		assertEquals("./system.properties", System.getProperty("file.props"));
	}
	
	@Test
	public void test() {
		assertEquals("./system.properties", System.getProperty("file.props"));
	}
}
