package br.com.zebys.makeatest;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

import br.com.zebys.makeatest.proxy.MakeATestProxy;

/**
 * Classe que extende o BlockJUnit4ClassRunner para ser utilizada como Runner do JUunit
 */
public class MakeATestRunner extends BlockJUnit4ClassRunner {
	
	/**
	 * Contrutor qye recebe uma classe e faz uma chamada para o contrutor do BlockJUnit4ClassRunner
	 * 
	 * @param klass
	 * @throws InitializationError
	 */
	public MakeATestRunner(Class<?> klass) throws InitializationError {
		super(klass);
	}
	
	/**
	 * Sobre escrita do método da classe principal para a inclusão de um proxy
	 * Neste proxy é possével interceptar todas as chamadas da classe de teste 
	 */
	@Override
	protected Object createTest() throws Exception {
		return MakeATestProxy.getProxy(getTestClass().getOnlyConstructor().newInstance());
	}

}
