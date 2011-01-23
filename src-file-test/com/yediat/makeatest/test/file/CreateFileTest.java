package com.yediat.makeatest.test.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.yediat.makeatest.file.CreateFile;
import com.yediat.makeatest.junit.MakeATestRunner;

@RunWith(MakeATestRunner.class)
public class CreateFileTest {

	private static final String CREATE_FILE = "./fileContent.txt";

	@After
	public void init() {
		File file = new File(CREATE_FILE);
		if (file.exists()) {
			file.delete();
		}
	}

	@Test
	@CreateFile(name = CREATE_FILE, content = "bla")
	public void createFile() {
		File file = new File(CREATE_FILE);
		try {
			BufferedReader br = new BufferedReader(new FileReader(file));
			StringBuffer content = new StringBuffer();
			String line = null;
			int lineNumber = 0;
			while ((line = br.readLine()) != null) {
				if(lineNumber>0) content.append("\n");
				content.append(line);
				lineNumber++;
			}

			Assert.assertEquals("bla", content.toString());

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
