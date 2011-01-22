package com.yediat.makeatest.test.file;

import java.io.File;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.yediat.makeatest.file.CreateFile;
import com.yediat.makeatest.junit.MakeATestRunner;


@RunWith(MakeATestRunner.class)
public class CreateFileTest {
	
	private static final String CREATE_FILE = "./fileContent.txt";
	
	//@Before
	@After
	public void init() {
		File file = new File(CREATE_FILE);
		if(file.exists()) {
			file.delete();
		}
	}
	
	@Test
	@CreateFile(name=CREATE_FILE, content="bla")
	public void createFile() {
		
		    File file = new File(CREATE_FILE);
		    
		
	}
		
}
