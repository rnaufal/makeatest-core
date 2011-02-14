package com.yediat.makeatest.annotations.testexception;

import java.io.File;


public class ExceptionInReader {
	
	private static final String FILE_EXISTS_ANNOTATION = "./fileExistsAnnotation.txt";
	
	@FileExistsExceptionAnnotation(filePath=FILE_EXISTS_ANNOTATION,failType="reader")
	public void assertErrorInVerifyFileExists() {
		@SuppressWarnings("unused")
		File file = new File(FILE_EXISTS_ANNOTATION);
	}
	
}
