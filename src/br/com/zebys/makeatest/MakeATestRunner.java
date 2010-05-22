package br.com.zebys.makeatest;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

import br.com.zebys.makeatest.proxy.MakeATestProxy;

public class MakeATestRunner extends BlockJUnit4ClassRunner {
	
	public MakeATestRunner(Class<?> klass) throws InitializationError {
		super(klass);
	}
	
	@Override
	protected Object createTest() throws Exception {
		return MakeATestProxy.getProxy(getTestClass().getOnlyConstructor().newInstance());
	}

}
