package br.com.zebys.makeatest.test.annotation;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.annotation.Annotation;

import br.com.zebys.makeatest.MakeATestExecuteInterface;

public class FileExistsExecute implements MakeATestExecuteInterface {

	public void execute(Annotation annotation) throws FileNotFoundException {
		System.out.println("Execute file exists");
		System.out.println(annotation.annotationType());
		FileExists fileExists = (FileExists) annotation;
		String path = fileExists.filePath();
		File f = new File(path);
		System.out.println("File: " + path);
		if (f.exists()) {
			System.out.println("File exists!!!");
		} else {
			System.out.println("File not found!!");
			throw new FileNotFoundException();
		}
	}

}
