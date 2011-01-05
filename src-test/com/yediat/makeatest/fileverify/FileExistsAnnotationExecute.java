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
	
	/**
	 * Classe que processa as anotações dos métodos
	 * @param annotation Anotação
	 * @param method Método anotado
	 * @param objecto Objecto em execução
	 */
	@Override
	public void execute(Annotation annotation, Method method, Object object) throws FileNotFoundException {
		FileExistsAnnotation fileExists = (FileExistsAnnotation) annotation;
		String path = fileExists.filePath();
		File f = new File(path);
		if (!f.exists()) {
			throw new FileNotFoundException();
		}
	}

	/**
	 * Classe que processa as anotações dos FIELD
	 * @param annotation Anotação
	 * @param field Field que foi anotado
	 * @param objecto Objecto em execução
	 */
	@Override
	public void execute(Annotation annotation, Field field, Object object)
			throws Exception {
		// FIXME tem que implementar essa funcionalidade de anotação em FIELDs, mas tem que verificar se esse tipo de anotação é mesmo necessára.
		
	}
}
