package br.com.zebys.makeatest.test.asserts.fileexists;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import br.com.zebys.makeatest.MakeATestExecuteInterface;

public class FileNotExistsExecute implements MakeATestExecuteInterface {

	@Override
	public void execute(Annotation annotation, Method method, Object object) throws Exception {
		FileNotExists fileNotExists = (FileNotExists) annotation;
		String path = fileNotExists.filePath();
		File f = new File(path);
		if (f.exists()) {
			throw new Exception("File exists in " + path);
		}
	}

	@Override
	public void execute(Annotation annotation, Field field, Object object)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
