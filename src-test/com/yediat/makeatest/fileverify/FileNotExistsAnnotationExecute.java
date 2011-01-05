package com.yediat.makeatest.fileverify;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.yediat.makeatest.MakeATestExecuteInterface;

/**
 * Classe de execução da anotação {@link FileNotExistsAnnotation}.
 * @author marcusfloriano
 *
 */
public class FileNotExistsAnnotationExecute implements MakeATestExecuteInterface {

	@Override
	public void execute(Annotation annotation, Field field, Object instance) throws Exception {
		// TODO Implementar a execução da anotação nos Field

	}

	@Override
	public void execute(Annotation annotation, Method method, Object instance) throws Exception {
		FileNotExistsAnnotation fileNotExists = (FileNotExistsAnnotation) annotation;
		String path = fileNotExists.filePath();
		File f = new File(path);
		if (f.exists()) {
			throw new Exception("File exists in " + path);
		}
	}

}
