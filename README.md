Make A Test
===========

O Make A Test é uma ferramenta cujo o objetivo é prover ao desenvolvedor a verificação de efeitos externos causados em testes de unidade.

O desenvolvimento de testes de unidade de forma simples e óbvia passa a se tornar complexo quando há a necessidade de validar um efeito externo. Os casos como verificar o estado dos dados de um SGBD, validar a existência de um recurso ou mesmo realizar chamadas de sistema como por exemplo ter que iniciar um determinado serviço torna a criação do teste complexa.

Considere que efeito externo é o resultado de uma execução de código, sendo que esse resultado está fora do domínio do sistema desenvolvido. Dessa forma, para validar por exemplo se uma determinada informação foi inserida corretamente dentro de um SGBD é necessário utilizar recursos que estão fora do sistema desenvolvido. O [DBUnit](http://www.dbunit.org) permite que verificações no SGBD sejam feitas de forma independente.

O Make A Test permite ao desenvolvedor criar essas verificações de efeitos externos e adicioná-las em testes de unidade através de anotações, deixando-os simples e de fácil entendimento.

Pacotes
-------

O Make A Test está dividido em pacotes:

- [makeatest-core](http://github.com/marcusfloriano/makeatest-core.git) -- `git@github.com:marcusfloriano/makeatest-core.git`

Contem toda a lógica necessária para a execução do framework

- [makeatest-junit](http://github.com/marcusfloriano/makeatest-junit.git) -- `git@github.com:marcusfloriano/makeatest-junit.git`

Integração com o JUnit 4 utilizando a funcionalidade Runners, é adicionado no teste de unidade o Runner abaixo:

    @RunWith(MakeATestRunner.class)

- [makeatest-dbunit](http://github.com/marcusfloriano/makeatest-dbunit.git) -- `git@github.com:marcusfloriano/makeatest-dbunit.git`

Contem as anotações para carregar e validar o estado de um SGBD, é um adaptador do DBUnit para o MakeATest.

Instalação
----------

Adicione no Classpath os pacotes jars:

- makeatest-core
 - [https://github.com/marcusfloriano/makeatest-core/blob/master/dist/makeatest-core-X.X.X.jar](https://github.com/marcusfloriano/makeatest-core/blob/master/dist/)
- makeatest-junit
 - [https://github.com/marcusfloriano/makeatest-junit/blob/master/dist/makeatest-junit-X.X.X.jar](https://github.com/marcusfloriano/makeatest-junit/blob/master/dist/)

Nos testes adicione o Runner como no exemplo abaixo.

	@RunWith(MakeATestRunner.class)
	public class VerifyPropertiesTest {
		...
	}

E basta utilizar as anotações disponíveis ou desenvolver sua própria anotação.

Desenvolvendo uma anotação
--------------------------

### Exemplo de uso

Para exemplificar o uso da ferramenta considere a necessidade de verificar se um determinado arquivo de propriedade foi criado corretamente a partir da classe `Properties`.

Dessa forma em um determinado teste de unidade temos:

	@Test
	public void verifyFileProperty() {
		Properties properties = new Properties();
		properties.setProperty("company_name", "Make A Test");
		properties.store(new FileOutputStream("filename.properties"), null);
	}
	
Agora é necessário que seja validado se o resultado do arquivo gerado contem a propriedade "company_name" com valor igual a "Make a Test". Essa validação pode ser feita apenas com a adição de uma anotação, como por exemplo:

	@ValidatePropertyFile(file="filename.properties", property="company_name", value="Make a Test")

### Criando a anotação

Inicialmente considere que a anotação criada será adicionada somente em métodos. Em um outro momento será demonstrado como desenvolver anotações para inserir em outros pontos na classe.

Abaixo temos o código da anotação que representa o trecho anterior para anotar métodos.

	@Target({ ElementType.METHOD })
	@Retention(RetentionPolicy.RUNTIME)
	public @interface ValidatePropertyFile {
		String file();
		String property();
		String value();
	}

### Criando o Make a Test Reader

O Make a Test processa a anotação em duas fases, sendo a primeira de leitura (Reader) e a segunda de processamento (Processor).
O Reader é responsável pela leitura e tratamento das informações passadas na anotação. Vejamos a anotação:

	@ValidatePropertyFile(file="filename.properties", property="company_name", value="Make a Test")

Para que o Make a Test realize a leitura dessa anotação é necessário criar uma classe de Reader para ela. Essa classe implementa a interface MakeATestReaderInterface<E> sendo que E é a anotação. Como consequencia essa classe de reader deve implementar o método "readAnnotation" conforme o exemplo abaixo. 

	public class ValidatePropertyFileReader implements MakeATestReaderInterface<ValidatePropertyFile> {
		@Override
		public void readAnnotation(ValidatePropertyFile annotation, AnnotationProperties descriptor) {
			if(annotation.property().trim().equals("")){
				throw new MakeATestInitalizationException("Property is empty");
			}
		}
	}
	
O Reader recupera o valor do property e do value, e neste momento de Reader é possível implementar as verificações necessárias. Nesse caso, por exemplo, podemos afirmar que o property é obrigatório. Há então a necessidade de implementar uma verificação e caso esse valor seja branco é lançada uma exceção, conforme o exemplo anterior.

Observe que caso o property for vazio será lançado uma exceção do tipo MakeATestInitializationException. Isso é devido ao ciclo de vida do Make a Test, no qual o Reader é a fase de inicialização.

### Criando o Make a Test Processor

Após a fase do Reader é necessário processar a anotação para isso tem que criar uma classe que estende a classe "MetadataProcessor".

	public class ValidatePropertyFileProcessor extends MetadataProcessor {
		private String property;
		private String value;
		private String file;
		
		public ValidatePropertyFileProcessor(String file, String property, String value) {
			this.file = file;
			this.property = property;
			this.value = value;
		}
		
		@Override
		public void process(Object instance) throws MakeATestAssertionError {
			try {
				Properties props = new Properties();
				props.load(new FileInputStream(this.file));
				Assert.assertEquals(this.value,props.getProperty(this.property));
			} catch (FileNotFoundException e) {
				throw new MakeATestException(e.getMessage());
			} catch (IOException e) {
				throw new MakeATestException(e.getMessage());
			}
		}
	}

A classe acima representa o "processor" implementado, o método "process" será executado quando o ciclo de vida do Make a Test fazer a chamada para essa método. Note também que as exceções são passadas por MakeaTestException dessa forma é apresentar o erro correto no stack no eclipse.

### Integrando a anotação, reader e o processor

A integração entre a anotação, reader e o processo para que ciclo de vida do Make a Test funcione é feita de forma bem simples, primeiro a classe "ValidatePropertyFileProcessor" recebe os dados pelo método construtor, essa classe é iniciada na classe reader e o método processor é chamado pelo Make a Test, segue a classe reader com a integração com o processor.

	public class ValidatePropertyFileReader implements MakeATestReaderInterface<ValidatePropertyFile> {
		@Override
		public void readAnnotation(ValidatePropertyFile annotation, AnnotationProperties properties) {
			if(annotation.property().trim().equals("")){
				throw new MakeATestInitalizationException("Property is empty");
			}
			**descriptor.setProcessor(new ValidatePropertyFileProcessor(annotation.file,annotation.property,annotation.value));**
		}
	}

Note que é criado o objeto do processor e adicionado no "properties.setProcessor(..)" que é recebido pelo readAnnotation, assim o Make a Test recupera do properties e executa o método "process".

Por fim é preciso informar na anotação "@ValidatePropertyFile" uma anotação do make a test para informar qual o Reader dessa anotação, se a execução do processo deve ser antes ou depois da execução do método e se é uma anotação para ser executado em momento de LOAD ou em momento de execução do método (PROXYMETHOD), segue o exemplo da anotação.

	@Target({ ElementType.METHOD })
	@Retention(RetentionPolicy.RUNTIME)
	@MakeATestReader(reader=ValidatePropertyFileReader.class,proxyBehavior={MakeATestProxyBehavior.AFTER},scope=MakeATestScope.PROXYMETHOD)
	public @interface ValidatePropertyFile {
		String file();
		String property();
		String value();
	}

### Testando o funcionamento

Para finalizar é necessário no teste de unidade adicionar duas coisas, o Runner do Make a Test e a anotação de verificação, segue abaixo a classe do teste de unidade com a anotação.

	@RunWith(MakeATestRunner.class)
	public class FileExistsAnnotationTest {

		@ValidatePropertyFile(file="filename.properties", property="company_name", value="Make a Test")
		@Test
		public void verifyFileProperty() {
			Properties properties = new Properties();
			properties.setProperty("company_name", "Make A Test");
			properties.store(new FileOutputStream("filename.properties"), null);
		}

	}
	
Dessa forma caso o property for diferente do value após a execução do método é retornado um AssertErro.
