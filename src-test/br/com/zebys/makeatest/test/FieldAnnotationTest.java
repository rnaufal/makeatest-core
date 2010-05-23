package br.com.zebys.makeatest.test;

import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.zebys.makeatest.MakeATestRunner;
import br.com.zebys.makeatest.test.jmocha.Expects;
import br.com.zebys.makeatest.test.jmocha.Exemples.User;

@RunWith(MakeATestRunner.class)
public class FieldAnnotationTest {

	@Expects(expects = "setName", returns = "Ola set")
	public User user;
	
	@Test
	public void listUsers() {
		System.out.println(this.user.getName());
	}
	
}
