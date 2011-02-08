package com.yediat.makeatest.junit;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

import com.yediat.makeatest.core.MakeATestController;
import com.yediat.makeatest.core.MakeATestInitializationException;


/**
 * Classe que extende o BlockJUnit4ClassRunner para ser utilizada como Runner do JUunit
 */
public class MakeATestRunner extends BlockJUnit4ClassRunner {
	
	private MakeATestController makeATestController;
	
	/**
	 * Contrutor que recebe uma classe e faz uma chamada para o contrutor do BlockJUnit4ClassRunner
	 * 
	 * @param klass
	 * @throws InitializationError
	 * @throws MakeATestInitializationException 
	 */
	public MakeATestRunner(Class<?> klass) throws InitializationError, MakeATestInitializationException {
		super(klass);
		try {
			this.makeATestController = new MakeATestController(getTestClass().getOnlyConstructor().newInstance());
		} catch (Exception e) {
			throw new MakeATestInitializationException(e.getMessage(),e);
		}
	}
	
	/**
	 * Sobre escrita do método da classe principal para a inclusão de um proxy
	 * Neste proxy é possével interceptar todas as chamadas da classe de teste 
	 */
	@Override
	protected Object createTest() throws Exception {
		return makeATestController.getObjectInstanceProxy();
	}

}
