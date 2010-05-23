package br.com.zebys.makeatest.test.asserts.fileexists;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.annotation.Annotation;

import br.com.zebys.makeatest.MakeATestExecuteInterface;

public class FileExistsExecute implements MakeATestExecuteInterface {
	public void execute(Annotation annotation) throws FileNotFoundException {
		FileExists fileExists = (FileExists) annotation;
		String path = fileExists.filePath();
		File f = new File(path);
		if (!f.exists()) {
			throw new FileNotFoundException();
		}
	}
}
