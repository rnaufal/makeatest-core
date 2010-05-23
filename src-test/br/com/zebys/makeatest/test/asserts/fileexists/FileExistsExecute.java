package br.com.zebys.makeatest.test.asserts.fileexists;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

import br.com.zebys.makeatest.MakeATestMethodExecuteInterface;

public class FileExistsExecute implements MakeATestMethodExecuteInterface {
	public void execute(Annotation annotation, Method method, Object object) throws FileNotFoundException {
		FileExists fileExists = (FileExists) annotation;
		String path = fileExists.filePath();
		File f = new File(path);
		if (!f.exists()) {
			throw new FileNotFoundException();
		}
	}
}
