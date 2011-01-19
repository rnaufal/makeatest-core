package com.yediat.makeatest.test;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.yediat.makeatest.fileverify.FileExistsAnnotation;
import com.yediat.makeatest.junit.MakeATestRunner;


@RunWith(MakeATestRunner.class)
public class FileExistsAnnotationTest {
	
	private static final String FILE_EXISTS_ANNOTATION = "./fileExistsAnnotation.txt";
	
	@Before
	@After
	public void init() {
		File file = new File(FILE_EXISTS_ANNOTATION);
		if(file.exists()) {
			file.delete();
		}
	}
	
	@Test
	@FileExistsAnnotation(filePath=FILE_EXISTS_ANNOTATION)
	public void createFile() {
		try {
		    File file = new File(FILE_EXISTS_ANNOTATION);
		    file.createNewFile();
		} catch (IOException e) {
		}
	}
		
}
