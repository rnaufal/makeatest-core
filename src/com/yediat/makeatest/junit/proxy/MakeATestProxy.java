package com.yediat.makeatest.junit.proxy;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import com.yediat.makeatest.core.MakeATestController;
import com.yediat.makeatest.core.MakeATestEnum;

/**
 * Essa classe implementa o MethodInterceptor e cria um proxy para um objeto. Dessa forma é possível interceptar todas as chamadas feita para o objeto real.
 * Assim faz a chamada para o core do Make a Test a para sua execução.
 * @see 
 * 
 */
public class MakeATestProxy implements MethodInterceptor {

	private Object object;
	
	/**
	 * Construtor responsável por receber o objeto que representa a instância da classe de testes
	 * e criar uma instância do MetadataReader.
	 * 
	 * @param object
	 */
	private MakeATestProxy(Object object) {
		this.object = object;
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
		MakeATestController makeATest = new MakeATestController();
		makeATest.process(method, MakeATestEnum.PROCESS_BEFORE);
		makeATest.process(method, MakeATestEnum.PROCESS_BOTH);
		Object objectForInvoke =  method.invoke(this.object, args);
		makeATest.process(method, MakeATestEnum.PROCESS_BOTH);
		makeATest.process(method, MakeATestEnum.PROCESS_AFTER);
		return objectForInvoke;
	}

	@SuppressWarnings("unchecked")
	/**
	 * Método responsável em criar um proxy a partir de um objeto possíbilidando interceptar todas as chamadas para esse objeto
	 * @param object Objeto passado para ser criado o proxy e interceptar todas as chamadas para esse objeto
	 */
	public static <E> E getProxy(E object) {
		try {
			MakeATestProxy proxy = new MakeATestProxy(object);
			Enhancer e = new Enhancer();
			e.setSuperclass(object.getClass());
			e.setCallback(proxy);
			return (E) e.create();
		} catch (Throwable e) {
			throw new Error(e.getMessage());
		}
	}

}
