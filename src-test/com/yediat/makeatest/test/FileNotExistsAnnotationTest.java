package com.yediat.makeatest.test;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.yediat.makeatest.annotations.fileverify.FileNotExistsAnnotation;
import com.yediat.makeatest.junit.MakeATestRunner;

@RunWith(MakeATestRunner.class)
public class FileNotExistsAnnotationTest {
	private static final String FILE_EXISTS_ANNOTATION = "./fileExistsAnnotation.txt";
	
	@Before
	public void init() {
		File file = new File(FILE_EXISTS_ANNOTATION);
		if(!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@After
	public void end() {
		File file = new File(FILE_EXISTS_ANNOTATION);
		if(file.exists()) {
			file.delete();
		}
	}
	
	@Test
	@FileNotExistsAnnotation(filePath=FILE_EXISTS_ANNOTATION)
	public void removeFile() {
	    File file = new File(FILE_EXISTS_ANNOTATION);
	    file.delete();
	}
}
