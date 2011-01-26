package com.yediat.makeatest.test;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.yediat.makeatest.core.MakeATestException;
import com.yediat.makeatest.fileverify.FileExistsExceptionAnnotation;
import com.yediat.makeatest.junit.MakeATestRunner;

@RunWith(MakeATestRunner.class)
public class FileExistsExceptionInProcessorTest {

	private static final String FILE_EXISTS_ANNOTATION = "./fileExistsAnnotation.txt";
	
	@Test(expected=MakeATestException.class)
	@FileExistsExceptionAnnotation(filePath=FILE_EXISTS_ANNOTATION,failType="processor")
	public void exceptionInProcessor() {
		@SuppressWarnings("unused")
		File file = new File(FILE_EXISTS_ANNOTATION);
	}
	
}
