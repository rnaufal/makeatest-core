package com.yediat.makeatest.test.file;

import java.io.File;
import java.io.IOException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.yediat.makeatest.file.DeleteFile;
import com.yediat.makeatest.junit.MakeATestRunner;

@RunWith(MakeATestRunner.class)
public class DeleteFileTest {

	private static final String DELETE_FILE = "./fileContent.txt";

	@Before
	public void init() {
		File file = new File(DELETE_FILE);
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	@DeleteFile(name = DELETE_FILE)
	public void deleteFile() {
		File file = new File(DELETE_FILE);
		Assert.assertFalse(file.exists());
	}

}
