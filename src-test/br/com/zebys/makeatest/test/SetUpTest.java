package br.com.zebys.makeatest.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.zebys.makeatest.MakeATestRunner;

@RunWith(MakeATestRunner.class)
public class SetUpTest {

	List<String> usersName;
	
	@Before
	public void setup(){
		this.usersName = new ArrayList<String>(){{add("Jonh");add("Elem");add("Pool");}};
	}
	
	@Test
	public void listUsers() {
		System.out.println(this.usersName);
	}
	
}
