package com.yediat.makeatest.test;

import org.junit.Test;

import com.yediat.makeatest.annotations.proxymethod.ProxyMethodClassCastExceptionInReader;
import com.yediat.makeatest.core.MakeATestController;
import com.yediat.makeatest.core.MakeATestException;
import com.yediat.makeatest.core.MakeATestInitializationException;

import static org.junit.Assert.*;

public class ProxyMethodClassCastExceptionInReaderTest {
	@Test
	public void shouldClassCastExceptionInReader() throws MakeATestException{
		try {
			MakeATestController makeATestController = new MakeATestController(new ProxyMethodClassCastExceptionInReader());
			ProxyMethodClassCastExceptionInReader proxyMethodClassCastExceptionInReader = (ProxyMethodClassCastExceptionInReader) makeATestController.getObjectInstanceProxy();
			proxyMethodClassCastExceptionInReader.proxyMethodClassCastExceptionInReader();
			assertTrue(false);
		} catch (MakeATestInitializationException e) {
			String message = e.getMessage().replaceAll("\\n", "");
			assertTrue(message.matches(".*\\(LoadAnnotation\\) ProxyMethodClassCastExceptionInReaderAnnotation.*com.yediat.makeatest.annotations.loadannotation.LoadAnnotationReader"));
		}
		
	}

}
