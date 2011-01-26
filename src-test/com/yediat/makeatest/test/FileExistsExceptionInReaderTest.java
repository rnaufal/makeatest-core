package com.yediat.makeatest.test;

import java.io.File;

import org.junit.Test;
import org.junit.runner.RunWith;

import com.yediat.makeatest.core.MakeATestInitializationException;
import com.yediat.makeatest.fileverify.FileExistsExceptionAnnotation;
import com.yediat.makeatest.junit.MakeATestRunner;


@RunWith(MakeATestRunner.class)
public class FileExistsExceptionInReaderTest {
	
	private static final String FILE_EXISTS_ANNOTATION = "./fileExistsAnnotation.txt";
	
	@Test(expected=MakeATestInitializationException.class)
	@FileExistsExceptionAnnotation(filePath=FILE_EXISTS_ANNOTATION,failType="none")
	public void assertErrorInVerifyFileExists() {
		@SuppressWarnings("unused")
		File file = new File(FILE_EXISTS_ANNOTATION);
	}
	
	@FileExistsExceptionAnnotation(filePath=FILE_EXISTS_ANNOTATION,failType="reader")
	public void exceptionInReader() {	
	}
}
