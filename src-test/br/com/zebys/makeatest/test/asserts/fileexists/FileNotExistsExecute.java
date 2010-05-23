package br.com.zebys.makeatest.test.asserts.fileexists;

import java.io.File;
import java.lang.annotation.Annotation;

import br.com.zebys.makeatest.MakeATestExecuteInterface;

public class FileNotExistsExecute implements MakeATestExecuteInterface {

	@Override
	public void execute(Annotation annotation) throws Exception {
		FileNotExists fileNotExists = (FileNotExists) annotation;
		String path = fileNotExists.filePath();
		File f = new File(path);
		if (f.exists()) {
			throw new Exception("File exists in " + path);
		}
	}

}
