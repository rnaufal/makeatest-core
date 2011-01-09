package com.yediat.makeatest.fileverify;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.yediat.makeatest.MakeATestExecuteInterface;

/**
 * Classe responsável pelo processamento da anotação
 * @author marcusfloriano
 *
 */

public class FileExistsAnnotationExecute implements MakeATestExecuteInterface {
	
	@Override
	public void execute(Annotation annotation, Method method, Object object) throws FileNotFoundException {
		FileExistsAnnotation fileExists = (FileExistsAnnotation) annotation;
		String path = fileExists.filePath();
		File f = new File(path);
		if (!f.exists()) {
			throw new FileNotFoundException();
		}
	}

	@Override
	public void execute(Annotation annotation, Field field, Object object)
			throws Exception {
		//FIXME Implementar essa funcionalidade de anotação.
	}

}
