package com.yediat.makeatest.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.yediat.makeatest.annotations.systemproperty.SystemPropertyLoad;
import com.yediat.makeatest.junit.MakeATestRunner;


@RunWith(MakeATestRunner.class)
@SystemPropertyLoad(key="key0",value="value0")
public class SystemPropertyLoadMultTest {
		
	@Test
	@SystemPropertyLoad(key="key1",value="value1")
	public void shouldGetOneProperty() {
		assertEquals("value1", System.getProperty("key1"));
	}

	@Test
	@SystemPropertyLoad(key="key2",value="value2")
	public void shouldGetTwoProperties() {
		assertEquals("value1", System.getProperty("key1"));
		assertEquals("value2", System.getProperty("key2"));
	}

	@Test
	public void shouldGetPropertiesSetInClass() {
		assertEquals("value0", System.getProperty("key0"));
	}

}
