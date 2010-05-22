package br.com.zebys.makeatest;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.junit.internal.runners.model.EachTestNotifier;
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
	protected Object createTest() throws Exception {
		return MakeATestProxy.getProxy(getTestClass().getOnlyConstructor().newInstance());
	}
	
	//@Override
	public void run2(final RunNotifier notifier) {
		try {
			Object object = MakeATestProxy.getProxy(klass.newInstance());
			
			List<FrameworkMethod> methods = computeTestMethods();
			for (FrameworkMethod frameworkMethod : methods) {
				try {
					object.getClass().getMethod(frameworkMethod.getName()).invoke(object);
				} catch (InvocationTargetException e) {
					EachTestNotifier testNotifier = new EachTestNotifier(notifier,getDescription());
					testNotifier.addFailure(e);
				}
			}
		}  catch (Exception e) {
			//e.printStackTrace();
		}
	}

}
