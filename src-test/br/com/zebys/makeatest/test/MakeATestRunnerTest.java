package br.com.zebys.makeatest.test;

import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.zebys.makeatest.MakeATestRunner;
import br.com.zebys.makeatest.test.annotation.FileExists;
import br.com.zebys.makeatest.test.annotation.FileExistsExecute;



@RunWith(MakeATestRunner.class)
public class MakeATestRunnerTest {
	
	@Test
	public void runner() {
		String firstName = "Marcus";
		String lastName = null;
		System.out.println("Test execute");
	}
	
	@Test
	@FileExists(klass=FileExistsExecute.class,filePath="./file.txt")
	public void runne2() {
		String firstName = "Marcus";
		String lastName = null;
		System.out.println("Test execute 2");
	}
}
