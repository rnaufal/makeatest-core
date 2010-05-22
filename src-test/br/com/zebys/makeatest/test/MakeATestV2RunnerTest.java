package br.com.zebys.makeatest.test;

import junit.framework.TestCase;
import br.com.zebys.makeatest.proxy.MakeATestV2Proxy;
import br.com.zebys.makeatest.test.annotation.FileExists;

public class MakeATestV2RunnerTest extends TestCase {
	
	public interface TesteInterf {
		public String metodoSemAnotacao();		
		@FileExists(filePath="/etc/hosts")
		public String metodoArquivoExiste();
		@FileExists(filePath="/etc/xpto")
		public String metodoArquivoNaoExiste();
	}
	
	public class TesteClass implements TesteInterf {
		public String metodoSemAnotacao() {
			return "OK1";
		}
		public String metodoArquivoExiste() {
			return "OK2";
		}
		public String metodoArquivoNaoExiste() {
			return "OK3";
		}		
	}
	
	public TesteInterf target;
	
	protected void setUp() throws Exception {
		target = (TesteInterf) MakeATestV2Proxy.createProxy(new TesteClass());
	}
	
	public void testSemArquivo(){
		try {
			String retorno = target.metodoArquivoNaoExiste();
			fail("O arquivo não existe");
		} catch (RuntimeException e) {
			assertTrue(e instanceof RuntimeException);
		}
	}
	public void testComArquivo(){
		try {
			String retorno = target.metodoArquivoExiste();
			assertEquals("O arquivo existe",retorno,"OK2");
		} catch (RuntimeException e) {
			fail("O arquivo existe");
		}
	}
	public void testSemAnotacao(){
		try {
			String retorno = target.metodoSemAnotacao();
			assertEquals("O método deve ser executado",retorno,"OK1");
		} catch (RuntimeException e) {
			fail("Não precisa de arquivo");
		}
	}

}
