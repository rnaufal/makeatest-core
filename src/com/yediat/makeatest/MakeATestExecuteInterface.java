package com.yediat.makeatest;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Interface para a implementação da execução das anottações
 * @author marcusfloriano
 *
 */
public interface MakeATestExecuteInterface {
	
	/**
	 * Method para anotações que estão em metodos
	 * @param annotation - A própria anotação para recuperar os valores que são adicionados para ela
	 * @param method - Method que a anotação está presente
	 * @param instance - Instancia da classe de teste que está sendo processada no momento do teste
	 * @throws Exception
	 */
	public void execute(Annotation annotation, Method method, Object instance) throws Exception;

	/**
	 * Método para anotações que são do tipo FIELD
	 * @param annotation - A própria anotação para recuperar os valores que são adicionados para ela
	 * @param field - Field que a anotação está presente
	 * @param instance - Instancia da classe de teste que está sendo processada no momento do teste
	 * @throws Exception
	 */
	// TODO Precisa analisar se teremos que porcessar os restantes das anotações
	public void execute(Annotation annotation, Field field, Object instance) throws Exception;

}
