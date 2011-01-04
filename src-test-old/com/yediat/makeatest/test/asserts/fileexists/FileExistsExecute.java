package com.yediat.makeatest.test.asserts.fileexists;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.yediat.makeatest.MakeATestExecuteInterface;


public class FileExistsExecute implements MakeATestExecuteInterface {
	public void execute(Annotation annotation, Method method, Object object) throws FileNotFoundException {
		FileExists fileExists = (FileExists) annotation;
		String path = fileExists.filePath();
		File f = new File(path);
		if (!f.exists()) {
			throw new FileNotFoundException();
		}
	}

	@Override
	public void execute(Annotation annotation, Field field, Object object)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
}
