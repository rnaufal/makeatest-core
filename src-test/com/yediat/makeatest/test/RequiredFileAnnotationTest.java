package com.yediat.makeatest.test;

import java.io.File;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.yediat.makeatest.annotations.fileverify.RequiredFileAnnotation;
import com.yediat.makeatest.junit.MakeATestRunner;

@RunWith(MakeATestRunner.class)
public class RequiredFileAnnotationTest {

	private static final String REQUIRED_FILE = "./requiredFileAnnotation.txt";

	@Before
	@After
	public void init() {
		File file = new File(REQUIRED_FILE);
		if (file.exists()) {
			file.delete();
		}
	}

	@Test
	@RequiredFileAnnotation(filePath = REQUIRED_FILE)
	public void requiredFile() {
		/*
		 * Como a anotação @RequiredFileAnnotation indica que o arquivo é
		 * requerido no ambiente e que se não existir vai ser criado, esse teste
		 * verifica se a classe que executa a anotação realmente criou o
		 * arquivo.
		 */
		File file = new File(REQUIRED_FILE);

		Assert.assertTrue(file.exists());
	}

}
