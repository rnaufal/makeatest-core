Make A Test
===========

O Make A Test é uma ferramenta cujo o objetivo é prover ao desenvolvedor a verificação de efeitos externos causados em testes de unidade.

O desenvolvimento de testes de unidade de forma simples e óbvia passa a se tornar complexo quando há a necessidade de validar um efeito externo. Os casos como verificar o estado dos dados de um SGBD, validar a existência de um recurso ou mesmo realizar chamadas de sistema como por exemplo ter que iniciar um determinado serviço torna a criação do teste complexa.

Considere que efeito externo é o resultado de uma execução de código, sendo que esse resultado está fora do domínio do sistema desenvolvido. Dessa forma, para validar por exemplo se uma determinada informação foi inserida corretamente dentro de um SGBD é necessário utilizar recursos que estão fora do sistema desenvolvido. O [DBUnit](http://www.dbunit.org) permite que verificações no SGBD sejam feitas de forma independente.

O Make A Test permite ao desenvolvedor criar essas verificações de efeitos externos e adicioná-las em testes de unidade através de anotações, deixando-os simples e de fácil entendimento.

Pacotes
-------

O Make A Test está dividido em pacotes:

- [makeatest-core](http://github.com/marcusfloriano/makeatest-core.git) -- `git@github.com:marcusfloriano/makeatest-core`

Contem toda a lógica necessária para a execução do framework

- [makeatest-junit](http://github.com/marcusfloriano/makeatest-junit.git) -- `git@github.com:marcusfloriano/makeatest-junit`

Integração com o JUnit 4 utilizando a funcionalidade Runners, é adicionado no teste de unidade o Runner abaixo:

    @RunWith(MakeATestRunner.class)

- [makeatest-dbunit](http://github.com/marcusfloriano/makeatest-dbunit.git) -- `git@github.com:marcusfloriano/makeatest-dbunit`

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
	
Agora é necessário que seja validado se o resultado do arquivo gerado contem a propriedade `company_name` com valor igual a `Make a Test`. Essa validação pode ser feita apenas com a adição de uma anotação, como por exemplo:

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

### Criando o Reader

O Make a Test processa a anotação em duas fases, sendo a primeira de leitura (Reader) e a segunda de processamento (Processor).
O Reader é responsável pela leitura e tratamento das informações passadas na anotação. Vejamos a anotação:

	@ValidatePropertyFile(file="filename.properties", property="company_name", value="Make a Test")

Para que o Make a Test realize a leitura dessa anotação é necessário criar uma classe de Reader para ela. Essa classe implementa a interface MakeATestReaderInterface<E> sendo que E é a anotação. Como consequencia essa classe de reader deve implementar o método `readAnnotation` conforme o exemplo abaixo. 

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

### Criando o Processor

Após a fase de leitura é necessário processar a anotação. Para a fase de processamento (Processor) é necessário criar uma classe que estende a classe `MetadataProcessor`.

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

A classe acima representa o `processor` implementado, na qual o método `process` contem a lógica de processamento da anotação. A chamada para esse método é responsabilidade do Make a Test, que a faz durante seu ciclo de vida. 


### Integrando a anotação, reader e o processor

Para que tudo funcione corretamente durante o ciclo de vida do Make A Test, a anotação, o reader e o processor precisam estar integrados. Essa integração é feita de forma bem simples. 

Primeiramente na classe de Reader é criada uma instância de um `ValidatePropertyFileProcessor` (Processor), e os valores necessários para o processamento da anotação são passados durante essa criação. Abaixo exemplo da integração do reader e processor.

	public class ValidatePropertyFileReader implements MakeATestReaderInterface<ValidatePropertyFile> {
		@Override
		public void readAnnotation(ValidatePropertyFile annotation, AnnotationProperties properties) {
			if(annotation.property().trim().equals("")){
				throw new MakeATestInitalizationException("Property is empty");
			}
			properties.setProcessor(new ValidatePropertyFileProcessor(annotation.file,annotation.property,annotation.value));
		}
	}

Note que o processor criado é adicionado no AnnotationProperties "properties.setProcessor(..)" recebido no readAnnotation. Isso permite ao Make A Test recuperar desse properties esse processor quando for necessário e assim invocar o método `process`.

Por fim é necessário relacionar a anotação com um Reader. Isso é feito utilizando inserindo uma anotação denominada `@MakeATestReader` na anotação criada (`@ValidatePropertyFile`). 
Utilizando a anotação do `@MakeATestReader` é possível informar qual a classe de Reader dessa anotação, se a execução do `processor` deve acontecer antes ou depois da execução do método e também se é uma anotação para ser executado em momento de carregamento (LOAD) ou em momento de execução do método (PROXYMETHOD). Segue exemplo de integração da anotação com reader abaixo.

	@Target({ ElementType.METHOD })
	@Retention(RetentionPolicy.RUNTIME)
	@MakeATestReader(reader=ValidatePropertyFileReader.class,proxyBehavior={MakeATestProxyBehavior.AFTER},scope=MakeATestScope.PROXYMETHOD)
	public @interface ValidatePropertyFile {
		String file();
		String property();
		String value();
	}

### Testando o funcionamento

Após tudo integrado, a anotação de verificação já está pronta para ser utilizada no teste de unidade. O Make a Test porém utiliza um Runner próprio, e antes de executar o caso de teste é preciso adicionar essa informação na classe através da anotação @RunWith.
Segue abaixo a classe do teste, com a anotação e informação de runner.

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
	
No teste acima, após a execução do método, se o 'property' estiver com um valor diferente do 'value' (ambos passados na anotação) esperado é obtido um AssertError e o teste falha. Caso contrário o teste é executado com sucesso.
