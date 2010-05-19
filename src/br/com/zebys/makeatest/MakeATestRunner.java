package br.com.zebys.makeatest;

import java.util.List;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.InitializationError;

import br.com.zebys.makeatest.proxy.MakeATestProxy;

public class MakeATestRunner extends BlockJUnit4ClassRunner {
private Class<?> klass;
	public MakeATestRunner(Class<?> klass) throws InitializationError {
		super(klass);
		this.klass = klass;
	}
	
	@Override
	public void run(final RunNotifier notifier) {
		try {
			Object object = MakeATestProxy.getProxy(klass.newInstance());
			List<FrameworkMethod> methods = computeTestMethods();
			for (FrameworkMethod frameworkMethod : methods) {
				object.getClass().getMethod(frameworkMethod.getName()).invoke(object);
			}
		}  catch (Exception e) {
			e.printStackTrace();
		}
	}

}
