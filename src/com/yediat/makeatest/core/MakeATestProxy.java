package com.yediat.makeatest.core;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;


/**
 * Essa classe implementa o MethodInterceptor e cria um proxy para um objeto. Dessa forma é possível interceptar todas as chamadas feita para o objeto real.
 * Assim faz a chamada para o core do Make a Test a para sua execução.
 * @see 
 * 
 */
public class MakeATestProxy implements MethodInterceptor {

	private MakeATestController makeATestController;
	
	/**
	 * Construtor responsável por receber o objeto que representa a instância da classe de testes
	 * 
	 * @param object
	 */
	private MakeATestProxy(Object object, MakeATestController makeATestController) throws MakeATestInitializationException {
		this.makeATestController = makeATestController;
	}

	/**
	 * Método que intercepta as chamadas para os métodos da classe de teste.
	 * Esse método sempre é chamado quando um método da classe interceptada for chamado.
	 * 
	 * @param obj Instância da classe de teste
	 * @param method Method que está sendo chamado
	 * @param args Argumentos passados para o método
	 * @param proxy O proxy do método
	 */
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		//return this.makeATestController.intercept(obj, method, args, proxy);
		return method.invoke(obj, args);
	}

	@SuppressWarnings("unchecked")
	/**
	 * Método responsável em criar um proxy a partir de um objeto possíbilitando interceptar todas as chamadas para esse objeto
	 * @param object Objeto passado para ser criado o proxy e interceptar todas as chamadas para esse objeto
	 */
	public static <E> E getProxy(E object, MakeATestController makeATestController) throws Exception {
		try {
			MakeATestProxy proxy = new MakeATestProxy(object,makeATestController);
			Enhancer e = new Enhancer();
			e.setSuperclass(object.getClass());
			e.setCallback(proxy);
			return (E) e.create();
		} catch (Exception e) {
			throw e;
		}
	}

}
