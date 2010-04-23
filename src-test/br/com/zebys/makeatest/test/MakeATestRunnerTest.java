package br.com.zebys.makeatest.test;

import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.zebys.makeatest.MakeATestRunner;
import br.com.zebys.makeatest.test.annotations.IsNotNull;



@RunWith(MakeATestRunner.class)
public class MakeATestRunnerTest {
	
	@Test
	public void runner() {
		String firstName = "Marcus";
		String lastName = null;
		
		@IsNotNull()
		String fullName = firstName + lastName;
	}
}
