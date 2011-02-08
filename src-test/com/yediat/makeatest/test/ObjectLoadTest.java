package com.yediat.makeatest.test;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.yediat.makeatest.annotations.objectload.ObjectLoad;
import com.yediat.makeatest.junit.MakeATestRunner;


@RunWith(MakeATestRunner.class)
public class ObjectLoadTest {

	@ObjectLoad("Load string in the object string")
	public String objectLoad;
	
	@Test
	public void test(){
		Assert.assertEquals("Load string in the object string", this.objectLoad);
	}
	
}
