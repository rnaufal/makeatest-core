package br.com.zebys.makeatest.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.zebys.makeatest.MakeATestRunner;
import br.com.zebys.makeatest.test.asserts.fileexists.FileExists;
import br.com.zebys.makeatest.test.asserts.fileexists.FileNotExists;

@RunWith(MakeATestRunner.class)
public class FileAnnotationTest {
	
	@Test
	public void runner() {
		String firstName = "Marcus";
		assertEquals("Marcus", firstName);
	}
	
	@Test
	@FileExists(filePath="/etc/hosts")
	public void runneFileExists() {
	}
	
	@Test
	@FileNotExists(filePath="./file.txt")
	public void runneFileNotExists() {
	}
}
