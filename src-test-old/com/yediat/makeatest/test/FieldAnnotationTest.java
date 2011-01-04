package com.yediat.makeatest.test;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.yediat.makeatest.MakeATestRunner;
import com.yediat.makeatest.test.jmocha.Expects;
import com.yediat.makeatest.test.jmocha.Exemples.User;


@RunWith(MakeATestRunner.class)
public class FieldAnnotationTest {

	@Expects(expects = "setName", returns = "Ola set")
	public User user;
	
	@Test
	public void listUsers() {
		System.out.println(this.user.getName());
	}
	
}
