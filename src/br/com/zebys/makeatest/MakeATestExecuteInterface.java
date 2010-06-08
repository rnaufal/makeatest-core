package br.com.zebys.makeatest;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Interface para a implementa��o da execu��o das anotta��es
 * @author marcusfloriano
 *
 */
public interface MakeATestExecuteInterface {
	/**
	 * Method para anota��es que est�o em Field
	 * @param annotation - A pr�pria anota��o para recuperar os valores que s�o adicionados para ela
	 * @param field - Field que a anota��o est� presente
	 * @param instance - Instancia da classe de teste que est� sendo processada no momento do teste
	 * @throws Exception
	 */
	public void execute(Annotation annotation, Field field, Object instance) throws Exception;
	
	/**
	 * Method para anota��es que est�o em metodos
	 * @param annotation - A pr�pria anota��o para recuperar os valores que s�o adicionados para ela
	 * @param method - Method que a anota��o est� presente
	 * @param instance - Instancia da classe de teste que est� sendo processada no momento do teste
	 * @throws Exception
	 */
	public void execute(Annotation annotation, Method method, Object instance) throws Exception;
}
