package com.yediat.makeatest.test;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.yediat.makeatest.core.MakeATestAssertionError;
import com.yediat.makeatest.fileverify.FileExistsAnnotation;
import com.yediat.makeatest.junit.MakeATestRunner;


@RunWith(MakeATestRunner.class)
public class FileExistsExceptionAnnotation {
	
	private static final String FILE_EXISTS_ANNOTATION = "./fileExistsAnnotation.txt";
	
	@Test(expected = MakeATestAssertionError.class)
	@FileExistsAnnotation(filePath=FILE_EXISTS_ANNOTATION)
	public void createFileAndExpectedMakeATestAssertionError() {
		@SuppressWarnings("unused")
		File file = new File(FILE_EXISTS_ANNOTATION);
	}
}
