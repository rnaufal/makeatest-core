package br.com.zebys.makeatest.test;

import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.zebys.makeatest.MakeATestRunner;
import br.com.zebys.makeatest.test.jmocha.Exemples.User;
import br.com.zebys.makeatest.test.mockito.Mock;

@RunWith(MakeATestRunner.class)
public class MockAnnotationTest {

	@Mock
	public User user;
	
	@Test
	public void getUser() {
		System.out.println(this.user);
	}
	
}
