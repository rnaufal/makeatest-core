package com.yediat.makeatest.test;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.yediat.makeatest.MakeATestRunner;
import com.yediat.makeatest.test.jmocha.Exemples.User;
import com.yediat.makeatest.test.mockito.Mock;


@RunWith(MakeATestRunner.class)
public class MockAnnotationTest {

	@Mock
	public User user;
	
	@Test
	public void getUser() {
		System.out.println(this.user);
	}
	
}
