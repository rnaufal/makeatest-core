package com.yediat.makeatest.annotations.testexception;

import java.io.File;

import com.yediat.makeatest.annotations.FailType;


public class ExceptionInReader {
	
	private static final String FILE_EXISTS_ANNOTATION = "./fileExistsAnnotation.txt";
	
	@FileExistsExceptionAnnotation(filePath=FILE_EXISTS_ANNOTATION,failType=FailType.READER)
	public void assertErrorInVerifyFileExists() {
		@SuppressWarnings("unused")
		File file = new File(FILE_EXISTS_ANNOTATION);
	}
	
}
