package br.com.zebys.makeatest.test;

import junit.framework.TestCase;
import br.com.zebys.makeatest.exception.MakeATestException;
import br.com.zebys.makeatest.proxy.MakeATestV2Proxy;
import br.com.zebys.makeatest.test.annotation.FileExists;
import br.com.zebys.makeatest.test.annotation.FileExistsExecute;

public class MakeATestV2RunnerTest extends TestCase {
	
	public interface TesteInterf {
		public String metodoSemAnotacao();		
		@FileExists(filePath="/etc/hosts", klass=FileExistsExecute.class)
		public String metodoArquivoExiste();
		@FileExists(filePath="/etc/xpto", klass=FileExistsExecute.class)
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
			assertTrue(e instanceof MakeATestException);
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
