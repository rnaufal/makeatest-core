package com.yediat.makeatest.test;

import java.io.File;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.yediat.makeatest.core.MakeATestAssertionError;
import com.yediat.makeatest.fileverify.FileExistsExceptionAnnotation;
import com.yediat.makeatest.junit.MakeATestRunner;


@RunWith(MakeATestRunner.class)
public class FileExistsAssertErrorTest {

	private static final String FILE_EXISTS_ANNOTATION = "./fileExistsAnnotation.txt";
	
	@Before
	public void init() {
		File file = new File(FILE_EXISTS_ANNOTATION);
		if(file.exists()){
			file.delete();
		}
	}
	
	@Test(expected=MakeATestAssertionError.class)
	@FileExistsExceptionAnnotation(filePath=FILE_EXISTS_ANNOTATION,failType="none")
	public void assertErrorInVerifyFileExists() {
		@SuppressWarnings("unused")
		File file = new File(FILE_EXISTS_ANNOTATION);
	}
	
}
