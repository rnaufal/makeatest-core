package com.yediat.makeatest.test.file;

import java.io.File;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.yediat.makeatest.file.CreateFile;
import com.yediat.makeatest.file.DeleteFile;
import com.yediat.makeatest.file.FileContains;
import com.yediat.makeatest.file.FileNotContains;
import com.yediat.makeatest.junit.MakeATestRunner;

@RunWith(MakeATestRunner.class)
public class SuiteFileTest {

	private static final String MY_FILE = "./myfileContent.txt";

	@Test
	@CreateFile(name = MY_FILE, content = "prop1=value1")
	@FileContains(name = MY_FILE, content = "prop1=value1")
	@FileNotContains(name = MY_FILE, content = "prop1=value2")
	//@DeleteFile(name = MY_FILE)
	public void createFile() {
		//File f = new File(MY_FILE);
		//Assert.assertEquals(f.exists(), false);
	}
}
